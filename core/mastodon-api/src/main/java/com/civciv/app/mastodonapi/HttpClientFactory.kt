/*
 * Copyright 2023 Fatih OZTURK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.civciv.app.mastodonapi

import com.civciv.app.mastodonapi.model.MastodonErrorResponse
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.utils.unwrapCancellationException
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlin.coroutines.cancellation.CancellationException

internal object HttpClientFactory {

    fun buildHttpClient(
        config: MastodonClientConfig,
    ): HttpClient {
        val defaultConfig: HttpClientConfig<*>.() -> Unit = {
            val json = JsonFactory.buildJson()

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = config.mastodonAuthCredentials?.loadDomainProvider?.invoke()
                        ?: MastodonWebConfig.MASTODON_HOST
                }
            }

            install(ContentNegotiation) {
                json(json)
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        config.mastodonAuthCredentials?.loadTokensProvider?.invoke()?.let {
                            BearerTokens(it, "")
                        }
                    }

                    sendWithoutRequest { true }
                }
            }

            expectSuccess = true
            HttpResponseValidator {
                handleResponseExceptionWithRequest { exception, _ ->
                    val clientException = exception as? ClientRequestException
                        ?: return@handleResponseExceptionWithRequest
                    val exceptionResponse = clientException.response
                    val mastodonErrorResponse = json.decodeMastodonErrorResponse(exceptionResponse)
                        ?: return@handleResponseExceptionWithRequest
                    throw MastodonException(mastodonErrorResponse, exception)
                }
            }

            install(HttpRequestRetry) {
                exponentialDelay()
                retryOnServerErrors(maxRetries = 3)

                retryOnExceptionIf(maxRetries = 3) { _, cause ->
                    when {
                        cause.isTimeoutException() -> false
                        cause is CancellationException -> false
                        cause is MastodonException -> false
                        else -> true
                    }
                }
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 60_000
                connectTimeoutMillis = 60_000
                socketTimeoutMillis = 60_000
            }

            config.httpClientConfigBlock?.invoke(this)
        }

        return config.httpClientBuilder?.invoke()?.config(defaultConfig)
            ?: HttpClient(defaultConfig)
    }

    private suspend fun Json.decodeMastodonErrorResponse(
        response: HttpResponse,
    ): MastodonErrorResponse? {
        if (!response.isMastodonStatusHandled) return null

        return try {
            val exceptionResponseText = response.bodyAsText()
            decodeFromString(MastodonErrorResponse.serializer(), exceptionResponseText).apply {
                statusCode = response.status.value
            }
        } catch (t: Throwable) {
            null
        }
    }

    private val HttpResponse.isMastodonStatusHandled: Boolean
        get() = status == HttpStatusCode.NotFound ||
            status == HttpStatusCode.Unauthorized ||
            status == HttpStatusCode.InternalServerError

    private fun Throwable.isTimeoutException(): Boolean {
        val exception = unwrapCancellationException()
        return exception is HttpRequestTimeoutException ||
            exception is ConnectTimeoutException ||
            exception is SocketTimeoutException
    }
}

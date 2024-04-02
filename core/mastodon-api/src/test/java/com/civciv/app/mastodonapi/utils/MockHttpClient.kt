/*
 * Copyright 2024 Fatih OZTURK
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
package com.civciv.app.mastodonapi.utils

import com.civciv.app.mastodonapi.Mastodon
import com.civciv.app.mastodonapi.MastodonClientConfig
import com.civciv.app.testing.ResourceReader
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.decodeURLPart
import io.ktor.http.headersOf

data class MastodonResponse(
    val endpoint: String,
    val response: String,
    val statusCode: HttpStatusCode = HttpStatusCode.OK,
)

fun buildMastodon(
    domain: String = Constants.TEST_DOMAIN,
    accessToken: String = Constants.TEST_ACCESS_TOKEN,
    responses: List<MastodonResponse>,
): Mastodon {
    val configuration = defaultMastodonConfiguration(domain, accessToken, responses)
    return Mastodon(configuration)
}

fun defaultMastodonConfiguration(
    domain: String,
    accessToken: String,
    responses: List<MastodonResponse>,
): MastodonClientConfig.() -> Unit = {
    val jsonFiles = mutableMapOf<String, Pair<String, HttpStatusCode>>()
    responses.forEach {
        jsonFiles["https://$domain/api/v1/${it.endpoint}"] = Pair(it.response, it.statusCode)
    }
    val headers = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))

    userAuthentication {
        loadAccessToken { accessToken }
        loadDomain { domain }
    }

    httpClient(MockEngine) {
        engine {
            addHandler { request ->
                val url = request.url.toString().decodeURLPart()
                val mockRequest = jsonFiles[url] ?: error("Unhandled url $url")
                val (fileName, statusCode) = mockRequest
                val content =
                    ResourceReader.readApiResponse(fileName) ?: error(
                        "File not found $fileName",
                    )
                respond(content = content, headers = headers, status = statusCode)
            }
        }
    }
}

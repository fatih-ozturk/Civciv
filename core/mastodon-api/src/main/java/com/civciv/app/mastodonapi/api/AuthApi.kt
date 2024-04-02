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
package com.civciv.app.mastodonapi.api

import com.civciv.app.mastodonapi.MastodonWebConfig
import com.civciv.app.mastodonapi.model.AccessTokenResponse
import com.civciv.app.mastodonapi.model.CredentialsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.submitForm
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import io.ktor.http.parameters
import io.ktor.http.path

class AuthApi internal constructor(
    private val client: HttpClient,
) {
    suspend fun registerApp(domain: String): CredentialsResponse {
        return client.submitForm(
            formParameters = parameters {
                append("client_name", MastodonWebConfig.APP_NAME)
                append("redirect_uris", "com.civciv.app://oauth2callback")
                append("scopes", MastodonWebConfig.AUTH_SCOPES)
            },
        ) {
            url {
                protocol = URLProtocol.HTTPS
                host = domain
                path("/api/v1/apps")
            }
        }.body<CredentialsResponse>().copy(scopes = MastodonWebConfig.AUTH_SCOPES, domain = domain)
    }

    suspend fun fetchOAuthToken(
        domain: String,
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        code: String,
        grantType: String,
    ): AccessTokenResponse {
        return client.submitForm(
            formParameters = parameters {
                append("client_id", clientId)
                append("client_secret", clientSecret)
                append("redirect_uri", redirectUri)
                append("code", code)
                append("grant_type", grantType)
            },
        ) {
            url {
                protocol = URLProtocol.HTTPS
                host = domain
                path("oauth/token")
            }
            method = HttpMethod.Post
        }.body()
    }
}

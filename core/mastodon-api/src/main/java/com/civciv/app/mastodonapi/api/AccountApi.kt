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

import com.civciv.app.mastodonapi.core.getByPaths
import com.civciv.app.mastodonapi.core.json
import com.civciv.app.mastodonapi.model.account.AccountResponse
import com.civciv.app.mastodonapi.model.request.AccountRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import io.ktor.http.path

class AccountApi internal constructor(
    private val client: HttpClient,
) {
    suspend fun verifyAccountCredentials(
        domain: String,
        accessToken: String,
    ): AccountResponse {
        return client.get {
            url {
                method = HttpMethod.Get
                protocol = URLProtocol.HTTPS
                host = domain
                path("/api/v1/accounts/verify_credentials")
            }
            header(HttpHeaders.Authorization, "Bearer $accessToken")
            json()
        }.body()
    }

    suspend fun getAccount(request: AccountRequest): AccountResponse {
        return client.getByPaths("accounts/${request.id}") {
            json()
        }
    }
}

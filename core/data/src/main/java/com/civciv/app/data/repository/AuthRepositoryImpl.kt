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
package com.civciv.app.data.repository

import com.civciv.app.base.inject.CivcivRedirectUri
import com.civciv.app.model.AccountCredentials
import com.civciv.app.model.ApplicationCredentials
import com.civciv.app.model.auth.AuthorizationResponse
import com.civciv.app.network.utils.Constants
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    @CivcivRedirectUri private val redirectUri: String,
) : AuthRepository {

    override fun getUserCredentials(): AccountCredentials? {
        return null
    }

    override suspend fun getApplicationCredentials(domain: String): ApplicationCredentials {
        return ApplicationCredentials(
            domain = domain,
            clientId = "registeredApp.clientId",
            clientSecret = "registeredApp.clientSecret",
            redirectUri = "registeredApp.redirectUri",
            scope = Constants.AUTH_SCOPES,
        )
    }

    override suspend fun setActiveAccount(accountId: String) {}

    override suspend fun addAccountCredentials(
        authorizationResult: AuthorizationResponse,
        applicationCredentials: ApplicationCredentials,
    ) {}
}

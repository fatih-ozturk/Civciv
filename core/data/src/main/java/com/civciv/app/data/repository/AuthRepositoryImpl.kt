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

import com.civciv.app.model.ApplicationCredentials
import com.civciv.app.network.api.AuthService
import com.civciv.app.network.utils.Constants
import javax.inject.Inject
import javax.inject.Named

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    @Named("civciv-redirect-uri") private val redirectUri: String,
) : AuthRepository {

    override suspend fun getAuthCredentials(domain: String): Result<ApplicationCredentials> =
        try {
            val registeredApp = authService.registerApp(
                domain = domain,
                redirectUris = redirectUri,
                clientName = Constants.APP_NAME,
                scopes = Constants.AUTH_SCOPES,
            )

            val applicationCredentials = with(registeredApp) {
                ApplicationCredentials(
                    domain = domain,
                    clientId = clientId,
                    clientSecret = clientSecret,
                    redirectUri = redirectUri,
                    scope = Constants.AUTH_SCOPES,
                )
            }
            Result.success(applicationCredentials)
        } catch (e: Exception) {
            Result.failure(e)
        }
}

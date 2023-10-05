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

import com.civciv.app.database.dao.AccountCredentialDao
import com.civciv.app.database.dao.AccountDao
import com.civciv.app.database.entities.account.AccountCredentialEntity
import com.civciv.app.database.entities.account.AccountEntity
import com.civciv.app.mastodonapi.api.AccountApi
import com.civciv.app.mastodonapi.api.AuthApi
import com.civciv.app.mastodonapi.model.CredentialsResponse
import com.civciv.app.mastodonapi.model.account.AccountResponse
import com.civciv.app.model.ApplicationCredentials
import com.civciv.app.model.AuthState
import com.civciv.app.model.auth.AuthorizationResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val accountCredentialDao: AccountCredentialDao,
    private val accountDao: AccountDao,
    private val authApi: AuthApi,
    private val accountApi: AccountApi,
) : AuthRepository {

    override suspend fun getApplicationCredentials(domain: String): ApplicationCredentials {
        return authApi.registerApp(domain = domain).toDomainModel()
    }

    override suspend fun setActiveAccount(accountId: String) {}

    override suspend fun addAccountCredentials(
        authorizationResult: AuthorizationResponse,
        applicationCredentials: ApplicationCredentials,
    ): Boolean {
        val accessTokenResponse = authApi.fetchOAuthToken(
            domain = applicationCredentials.domain,
            clientId = applicationCredentials.clientId,
            clientSecret = applicationCredentials.clientSecret,
            redirectUri = applicationCredentials.redirectUri,
            code = authorizationResult.code,
            grantType = "authorization_code",
        )
        val accountResponse = accountApi.verifyAccountCredentials(
            applicationCredentials.domain,
            accessTokenResponse.accessToken,
        )

        val accountCredentialEntity = AccountCredentialEntity(
            domain = applicationCredentials.domain,
            accessToken = accessTokenResponse.accessToken,
            tokenType = accessTokenResponse.tokenType,
            isActive = true,
            accountId = accountResponse.id,
        )

        accountCredentialDao.clearActiveAccountCredential()
        val userCredentialInsertResult =
            accountCredentialDao.insertAccountCredential(accountCredentialEntity)
        val userInsertResult = accountDao.insert(accountResponse.toEntityModel())
        return userInsertResult > 0 && userCredentialInsertResult > 0
    }

    override fun getAuthenticateState(): Flow<AuthState> {
        return accountCredentialDao.getActiveAccountCredentialFlow().map {
            /*
             * TODO we can verify account authorization if throws exception,
             *  we can change current account if there is any available.
            */
            if (it?.accessToken.isNullOrEmpty()) {
                AuthState.LOGGED_OUT
            } else {
                AuthState.LOGGED_IN
            }
        }.filterNotNull().distinctUntilChanged()
    }
}

fun CredentialsResponse.toDomainModel() = ApplicationCredentials(
    domain = domain,
    clientId = clientId,
    clientSecret = clientSecret,
    scope = scopes,
    redirectUri = redirectUri,
)

fun AccountResponse.toEntityModel(): AccountEntity = AccountEntity(
    accountId = id,
    username = username,
    profilePictureUrl = avatar,
)

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

import com.civciv.app.base.MainDispatcher
import com.civciv.app.database.dao.AccountsDao
import com.civciv.app.database.entities.AccountEntity
import com.civciv.app.model.AccountCredentials
import com.civciv.app.model.ApplicationCredentials
import com.civciv.app.model.auth.AuthorizationResponse
import com.civciv.app.network.api.AccountsService
import com.civciv.app.network.api.AuthService
import com.civciv.app.network.utils.Constants
import kotlinx.coroutines.CoroutineDispatcher
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val accountsDao: AccountsDao,
    private val accountsService: AccountsService,
    @Named("civciv-redirect-uri") private val redirectUri: String,
) : AuthRepository {

    override fun getUserCredentials(): AccountCredentials? {
        val currentAccount = accountsDao.getActiveAccount()
        return currentAccount?.let {
            AccountCredentials(
                domain = it.domain,
                accessToken = it.accessToken,
            )
        }
    }

    override suspend fun getApplicationCredentials(domain: String): ApplicationCredentials {
        val registeredApp = authService.registerApp(
            domain = domain,
            redirectUris = redirectUri,
            clientName = Constants.APP_NAME,
            scopes = Constants.AUTH_SCOPES,
        )

        return ApplicationCredentials(
            domain = domain,
            clientId = registeredApp.clientId,
            clientSecret = registeredApp.clientSecret,
            redirectUri = registeredApp.redirectUri,
            scope = Constants.AUTH_SCOPES,
        )
    }

    override suspend fun setActiveAccount(accountId: String) {
        val activeAccount = accountsDao.getActiveAccount()

        if (activeAccount != null) {
            accountsDao.update(activeAccount.copy(isActive = false))
        } else {
            Timber.i("Couldn't find any active account")
        }

        val newAccount = accountsDao.getAccount(accountId)

        if (newAccount != null) {
            accountsDao.update(newAccount.copy(isActive = true))
            return
        }

        val anyAccount = accountsDao.getAccounts().lastOrNull()
        if (anyAccount != null) {
            accountsDao.update(anyAccount.copy(isActive = true))
        } else {
            Timber.i("Couldn't find any account to set active")
        }
    }

    override suspend fun addAccountCredentials(
        authorizationResult: AuthorizationResponse,
        applicationCredentials: ApplicationCredentials,
    ) {
        val accessTokenResponse = authService.fetchOAuthToken(
            domain = applicationCredentials.domain,
            clientId = applicationCredentials.clientId,
            clientSecret = applicationCredentials.clientSecret,
            redirectUri = applicationCredentials.redirectUri,
            code = authorizationResult.code,
            grantType = "authorization_code",
        )

        val accountResponse = accountsService.verifyAccountCredentials(
            applicationCredentials.domain,
            "Bearer ${accessTokenResponse.accessToken}",
        )
        val accounts = accountsDao.getAccounts()
        accounts.filter { it.isActive }.onEach {
            accountsDao.insert(it.copy(isActive = false))
        }

        val existAccount = accounts.find { account ->
            applicationCredentials.domain == account.domain &&
                    accountResponse.id == account.id
        }

        val newAccount = if (existAccount != null) {
            existAccount.copy(
                isActive = true,
                accessToken = accessTokenResponse.accessToken,
            )
        } else {
            // TODO need mapper AccountResponse to AccountEntity
            AccountEntity(
                id = accountResponse.id,
                accessToken = accessTokenResponse.accessToken,
                domain = applicationCredentials.domain,
                isActive = true,
                username = accountResponse.localUsername,
            )
        }
        accountsDao.insert(newAccount)
    }
}

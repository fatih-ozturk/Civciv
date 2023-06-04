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

import com.civciv.app.database.dao.AccountsDao
import com.civciv.app.database.entities.AccountEntity
import com.civciv.app.database.entities.toExternalModel
import com.civciv.app.model.Account
import com.civciv.app.model.ApplicationCredentials
import com.civciv.app.network.model.AccessTokenResponse
import com.civciv.app.network.model.AccountResponse
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class AccountRepositoryImpl @Inject constructor(
    private val accountsDao: AccountsDao,
) : AccountRepository {
    override fun getActiveAccount(): Flow<Account> = flow {
        val accounts = accountsDao.getAccounts()
        val activeAccount = accountsDao.getActiveAccount()
        accounts.find { account -> account.isActive } ?: accounts.firstOrNull()
            ?.also { account -> account.isActive = true }

        if (activeAccount != null) {
            emit(activeAccount.toExternalModel())
        } else {
            throw NoSuchElementException("Couldn't find any active account")
        }
    }

    fun addAccount(
        applicationCredentials: ApplicationCredentials,
        accessTokenResponse: AccessTokenResponse,
        accountResponse: AccountResponse,
    ) {
        val accounts = accountsDao.getAccounts()
        accounts.find { it.isActive }?.let {
            accountsDao.insert(it.copy(isActive = false))
        }

        val existAccount = accounts.find { account ->
            applicationCredentials.domain == account.domain &&
                accountResponse.id == account.id
        }

        if (existAccount != null) {
            existAccount.copy(
                isActive = true,
                accessToken = accessTokenResponse.accessToken,
            ).also { newAccount ->
                accountsDao.insert(newAccount)
            }
        } else {
            AccountEntity(
                id = accountResponse.id,
                accessToken = accessTokenResponse.accessToken,
                domain = applicationCredentials.domain,
                isActive = true,
                username = accountResponse.username,
            ).also { newAccount ->
                accountsDao.insert(newAccount)
            }
        }
    }

    fun setActiveAccount(accountId: String) {
        val activeAccount = accountsDao.getActiveAccount()

        if (activeAccount != null) {
            accountsDao.update(activeAccount.copy(isActive = false))
        } else {
            Timber.i(NoSuchElementException("Couldn't find any active account"))
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
            throw NoSuchElementException("Couldn't find any account to set active")
        }
    }
}

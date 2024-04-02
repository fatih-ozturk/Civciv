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
package com.civciv.app.data.repository

import com.civciv.app.database.dao.AccountCredentialDao
import com.civciv.app.database.dao.AccountDao
import com.civciv.app.database.entities.account.AccountEntity
import com.civciv.app.database.entities.account.AccountWithCredential
import com.civciv.app.mastodonapi.api.AccountApi
import com.civciv.app.mastodonapi.model.request.AccountRequest
import com.civciv.app.model.Account
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountApi: AccountApi,
    private val accountCredentialDao: AccountCredentialDao,
    private val accountDao: AccountDao,
) : AccountRepository {
    override suspend fun updateCurrentAccount() {
        val currentUser = accountCredentialDao.getActiveAccountCredential() ?: throw Exception()
        val request = AccountRequest(currentUser.accountId)
        val account = accountApi.getAccount(request)
        accountDao.update(account.toEntityModel())
    }

    override suspend fun getCurrentAccount(): Account {
        val currentAccountCredential =
            accountCredentialDao.getActiveAccountCredential() ?: throw Exception()
        val currentUser = accountDao.getAccountById(currentAccountCredential.id)
            ?: throw Exception()
        return currentUser.toDomainModel()
    }

    override suspend fun getAuthorizedAccounts(): List<Account> {
        return accountDao.getAllAccountsWithCredential().map(
            AccountWithCredential::toDomainModel,
        )
    }

    override suspend fun changeAccount(accountId: String) {
        accountCredentialDao.clearActiveAccountCredential()
        accountCredentialDao.setActiveAccount(accountId)
        val accountRequest = AccountRequest(id = accountId)
        val user = accountApi.getAccount(accountRequest)
        accountDao.update(user.toEntityModel())
    }

    override suspend fun logoutCurrentUser() {
        val currentAccount = accountCredentialDao.getActiveAccountCredential()
        currentAccount?.let {
            accountCredentialDao.deleteAccountCredential(currentAccount)
            accountDao.deleteAccountById(currentAccount.accountId)
        }
        val accounts = accountCredentialDao.getAccountsCredential()
        if (accounts.isNotEmpty()) {
            val activeAccount = accounts.first()
            accountCredentialDao.setActiveAccount(activeAccount.accountId)
        }
    }
}

fun AccountEntity.toDomainModel(): Account {
    return Account(
        id = accountId,
        username = username,
        isActive = false,
    )
}

fun AccountWithCredential.toDomainModel(): Account {
    return Account(
        id = account.accountId,
        username = account.username,
        isActive = accountCredential.isActive,
    )
}

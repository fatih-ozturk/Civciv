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
import com.civciv.app.mastodonapi.api.AccountApi
import com.civciv.app.mastodonapi.model.request.AccountRequest
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
        accountDao.updateAccount(account.toEntityModel())
    }
}

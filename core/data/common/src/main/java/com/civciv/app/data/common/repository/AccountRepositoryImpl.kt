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
package com.civciv.app.data.common.repository

import com.civciv.app.database.dao.AccountsDao
import com.civciv.app.database.entities.toExternalModel
import com.civciv.app.model.Account
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountsDao: AccountsDao,
) : AccountRepository {
    override fun getActiveAccount(): Flow<Account?> = flow {
        val accounts = accountsDao.getAccounts()
        var activeAccount = accountsDao.getActiveAccount()

        activeAccount = activeAccount ?: accounts.firstOrNull()?.also { account ->
            account.isActive = true
            accountsDao.update(account)
        }
        emit(activeAccount?.toExternalModel())
    }
}

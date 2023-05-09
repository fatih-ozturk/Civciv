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
import com.civciv.app.database.entities.AccountDetailEntity
import com.civciv.app.database.entities.AccountEntity
import com.civciv.app.model.Account
import com.civciv.app.network.api.AccountsService
import com.civciv.app.network.model.AccountResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountsService: AccountsService,
    private val accountsDao: AccountsDao,
) : AccountRepository {

    override fun getActiveAccount(): Flow<Result<Account>> = flow {
        val accounts = accountsDao.getAllAccounts()
        val activeAccount =
            accounts.find { account -> account.credentials.isActive } ?: accounts.firstOrNull()
                ?.also { account -> account.credentials.isActive = true }

        if (activeAccount != null) {
            val accountResponse = accountsService.getAccount(activeAccount.detail.accountId)
            accountsDao.insert(
                accountDetail = accountResponse.toEntityModel(
                    detailId = activeAccount.credentials.id,
                ),
            )

            emit(Result.success(activeAccount.toExternalModel()))
        } else {
            emit(Result.failure(Exception()))
        }
    }
}

private fun AccountResponse.toEntityModel(detailId: Long): AccountDetailEntity {
    return AccountDetailEntity(
        detailId = detailId,
        accountId = id,
        username = username,
    )
}

private fun AccountEntity.toExternalModel(): Account = Account(
    username = detail.username,
)

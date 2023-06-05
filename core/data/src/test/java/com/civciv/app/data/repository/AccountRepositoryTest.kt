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

import app.cash.turbine.test
import com.civciv.app.database.dao.AccountsDao
import com.civciv.app.database.entities.AccountEntity
import com.civciv.app.database.entities.toExternalModel
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AccountRepositoryTest {

    private lateinit var accountsDao: AccountsDao
    private lateinit var repository: AccountRepository

    @Before
    fun setup() {
        accountsDao = mockk()
        repository = AccountRepositoryImpl(accountsDao)
    }

    @Test
    fun `getActiveAccount should emit the active account`() = runTest {
        val accountEntity = AccountEntity(
            id = "1",
            isActive = true,
            username = "username",
            domain = "domain",
            accessToken = "accessToken",
        )
        val accounts = listOf(
            accountEntity,
            accountEntity.copy(id = "2", domain = "domain2", isActive = false),
        )

        every { accountsDao.getAccounts() } returns accounts
        every { accountsDao.getActiveAccount() } returns accountEntity

        val mappedAccount = accountEntity.toExternalModel()

        repository.getActiveAccount().test {
            awaitItem() shouldBe mappedAccount
            awaitComplete()
        }
    }
}

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
package com.civciv.app.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.civciv.app.database.dao.AccountsDao
import com.civciv.app.database.entities.AccountEntity
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class AccountsDaoTest {

    private lateinit var accountsDao: AccountsDao
    private lateinit var database: CivcivDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            CivcivDatabase::class.java,
        ).allowMainThreadQueries().build()
        accountsDao = database.accountDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAccountAndGetById() = runTest {
        val account = AccountEntity(
            id = "1",
            isActive = true,
            username = "username",
            domain = "domain",
            accessToken = "accessToken",
        )

        accountsDao.insert(account)

        val getAccount = accountsDao.getAccount(account.id)

        getAccount?.id shouldBe account.id
    }

    @Test
    fun insertAndGetAccounts() {
        val account1 = AccountEntity(
            id = "1",
            isActive = true,
            username = "username",
            domain = "domain",
            accessToken = "accessToken",
        )
        val account2 = AccountEntity(
            id = "2",
            isActive = false,
            username = "username",
            domain = "domain2",
            accessToken = "accessToken",
        )

        accountsDao.insert(account1)
        accountsDao.insert(account2)

        val accounts = accountsDao.getAccounts()

        accounts.size shouldBe 2
        accounts[0] shouldBe account1
        accounts[1] shouldBe account2
    }

    @Test
    fun updateAccountAndVerifyIsUpdated() {
        val account = AccountEntity(
            id = "1",
            isActive = true,
            username = "username",
            domain = "domain",
            accessToken = "accessToken",
        )

        accountsDao.insert(account)

        val updatedAccount = account.copy(
            username = "updatedUsername",
            accessToken = "newAccessToken",
        )

        accountsDao.update(updatedAccount)

        val localUpdatedAccount = accountsDao.getAccount(account.id)

        updatedAccount shouldNotBe null
        updatedAccount.username shouldBe localUpdatedAccount?.username
        updatedAccount.accessToken shouldBe localUpdatedAccount?.accessToken
    }

    @Test
    fun deleteAccountAndVerify() {
        val account = AccountEntity(
            id = "1",
            isActive = true,
            username = "username",
            domain = "domain",
            accessToken = "accessToken",
        )

        accountsDao.insert(account)

        accountsDao.delete(account)

        val getDeletedAccount = accountsDao.getAccount(account.id)

        getDeletedAccount shouldBe null
    }
}

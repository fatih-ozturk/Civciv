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
package com.civciv.app.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.civciv.app.database.dao.AccountDao
import com.civciv.app.database.dao.StatusDao
import com.civciv.app.database.entities.account.AccountEntity
import com.civciv.app.database.entities.status.StatusEntity
import io.kotest.matchers.shouldBe
import java.io.IOException
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StatusDaoTest {
    private lateinit var statusDao: StatusDao
    private lateinit var accountDao: AccountDao
    private lateinit var database: CivcivDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, CivcivDatabase::class.java).build()
        statusDao = database.statusDao()
        accountDao = database.accountDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    fun insert_status_without_reblog_and_account_then_verify_status_with_account() =
        runTest {
            val account =
                AccountEntity(
                    accountId = "1",
                    username = "test",
                    profilePictureUrl = "https://example.com",
                )
            accountDao.insert(account)
            val status =
                StatusEntity(
                    statusId = "1",
                    statusAccountId = account.accountId,
                    createdAt = 0,
                    editedAt = null,
                    reblogsCount = 0,
                    favouritesCount = 0,
                    repliesCount = 0,
                    reblogged = false,
                    bookmarked = false,
                    favourited = false,
                    sensitive = false,
                    reblogId = null,
                    reblogAccountId = null,
                )
            statusDao.insert(status)

            val statuses = statusDao.getStatuses()
            statuses.size shouldBe 1
            statuses.first().statusEntity.statusId shouldBe status.statusId
            statuses.first().statusAccount.accountId shouldBe account.accountId
            statuses.first().reblogAccount shouldBe null
        }

    @Test
    fun insert_status_with_reblog_and_account_then_verify_status_with_account() =
        runTest {
            val account =
                AccountEntity(
                    accountId = "1",
                    username = "test",
                    profilePictureUrl = "https://example.com",
                )
            val reblogAccount =
                AccountEntity(
                    accountId = "2",
                    username = "test2",
                    profilePictureUrl = "https://example.com",
                )
            accountDao.insert(account)
            accountDao.insert(reblogAccount)
            val statusEntity =
                StatusEntity(
                    statusId = "1",
                    statusAccountId = account.accountId,
                    createdAt = 0,
                    editedAt = null,
                    reblogsCount = 0,
                    favouritesCount = 0,
                    repliesCount = 0,
                    reblogged = false,
                    bookmarked = false,
                    favourited = false,
                    sensitive = false,
                    reblogId = "2",
                    reblogAccountId = reblogAccount.accountId,
                )
            statusDao.insert(statusEntity)

            val statuses = statusDao.getStatuses()

            statuses.size shouldBe 1
            statuses.first().statusEntity.statusId shouldBe statusEntity.statusId
            statuses.first().statusAccount.accountId shouldBe account.accountId
            statuses.first().reblogAccount?.accountId shouldBe reblogAccount.accountId
        }
}

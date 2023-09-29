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
package com.civciv.app.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.civciv.app.database.entities.account.AccountEntity
import com.civciv.app.database.entities.account.AccountWithCredential

@Dao
interface AccountDao {
    @Query("SELECT * FROM account")
    suspend fun getAllAccount(): List<AccountEntity>

    @Query("SELECT * FROM account WHERE id = :accountId")
    suspend fun getAccountById(accountId: Long): AccountEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(user: AccountEntity): Long

    @Update
    suspend fun updateAccount(user: AccountEntity)

    @Delete
    suspend fun deleteAccount(user: AccountEntity)

    @Query("DELETE FROM account")
    suspend fun deleteAllAccount()

    @Query("DELETE FROM account WHERE accountId = :accountId")
    suspend fun deleteAccountById(accountId: String)

    @Transaction
    @Query("SELECT * FROM account WHERE accountId = :accountId")
    suspend fun getAccountWithCredential(accountId: String): AccountWithCredential?

    @Transaction
    @Query("SELECT * FROM account")
    suspend fun getAllAccountsWithCredential(): List<AccountWithCredential>
}

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
import androidx.room.Update
import com.civciv.app.database.entities.AccountEntity

@Dao
interface AccountsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(accountDetail: AccountEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(accountDetail: AccountEntity)

    @Delete
    fun delete(accountDetail: AccountEntity)

    @Query("SELECT * FROM AccountEntity ORDER BY updatedAt DESC")
    suspend fun getAccounts(): List<AccountEntity>

    @Query("SELECT * FROM AccountEntity WHERE id = :accountId")
    suspend fun getAccount(accountId: String): AccountEntity?

    @Query("SELECT * FROM AccountEntity WHERE isActive = 1")
    suspend fun getActiveAccount(): AccountEntity?
}

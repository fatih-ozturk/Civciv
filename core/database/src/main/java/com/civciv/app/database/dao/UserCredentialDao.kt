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
import com.civciv.app.database.entities.UserCredential

@Dao
interface UserCredentialDao {
    @Query("SELECT * FROM user_credential WHERE isActive = 1")
    fun getActiveUserCredential(): UserCredential?

    @Query("UPDATE user_credential SET isActive = 0 WHERE isActive = 1")
    suspend fun clearActiveUserCredential()

    @Query("UPDATE user_credential SET isActive = 1 WHERE accountId = :accountId")
    suspend fun setActiveUser(accountId: String)

    @Query("SELECT * FROM user_credential WHERE accountId = :accountId")
    suspend fun getUserCredentialForAccount(accountId: String): UserCredential?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserCredential(userCredential: UserCredential)

    @Delete
    suspend fun deleteUserCredential(userCredential: UserCredential)
}

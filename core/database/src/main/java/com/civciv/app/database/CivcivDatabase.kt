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

import androidx.room.Database
import androidx.room.RoomDatabase
import com.civciv.app.database.dao.AccountCredentialDao
import com.civciv.app.database.dao.AccountDao
import com.civciv.app.database.dao.StatusDao
import com.civciv.app.database.entities.account.AccountCredentialEntity
import com.civciv.app.database.entities.account.AccountEntity
import com.civciv.app.database.entities.status.StatusEntity

@Database(
    version = 1,
    exportSchema = true,
    entities = [
        AccountCredentialEntity::class,
        AccountEntity::class,
        StatusEntity::class,
    ],
)
abstract class CivcivDatabase : RoomDatabase() {
    abstract fun accountCredentialDao(): AccountCredentialDao
    abstract fun accountDao(): AccountDao
    abstract fun statusDao(): StatusDao
}

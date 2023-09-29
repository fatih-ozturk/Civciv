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
package com.civciv.app.database.di

import android.content.Context
import androidx.room.Room
import com.civciv.app.database.CivcivDatabase
import com.civciv.app.database.dao.AccountCredentialDao
import com.civciv.app.database.dao.AccountDao
import com.civciv.app.database.dao.StatusDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCivcivDatabase(
        @ApplicationContext context: Context,
    ): CivcivDatabase = Room.databaseBuilder(
        context,
        CivcivDatabase::class.java,
        "civciv-database",
    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

    @Provides
    @Singleton
    fun provideAccountCredentialDao(
        database: CivcivDatabase,
    ): AccountCredentialDao = database.accountCredentialDao()

    @Provides
    @Singleton
    fun provideAccountDao(
        database: CivcivDatabase,
    ): AccountDao = database.accountDao()

    @Provides
    @Singleton
    fun provideStatusDao(
        database: CivcivDatabase,
    ): StatusDao = database.statusDao()
}

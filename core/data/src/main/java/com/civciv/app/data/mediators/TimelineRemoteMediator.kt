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
package com.civciv.app.data.mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.civciv.app.database.dao.AccountDao
import com.civciv.app.database.dao.StatusDao
import com.civciv.app.database.entities.status.StatusWithAccount
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class TimelineRemoteMediator @Inject constructor(
    private val statusDao: StatusDao,
    private val accountDao: AccountDao,

) : RemoteMediator<Int, StatusWithAccount>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, StatusWithAccount>,
    ): MediatorResult {
        TODO("Not yet implemented")
    }

}

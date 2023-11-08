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

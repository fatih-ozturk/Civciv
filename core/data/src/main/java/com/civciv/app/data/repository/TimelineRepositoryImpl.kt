package com.civciv.app.data.repository

import androidx.paging.PagingSource
import com.civciv.app.database.entities.status.StatusWithAccount
import javax.inject.Inject

class TimelineRepositoryImpl @Inject constructor(

) : TimelineRepository  {

    override fun getTimeline(): PagingSource<Int, StatusWithAccount> {
        TODO()
    }
}
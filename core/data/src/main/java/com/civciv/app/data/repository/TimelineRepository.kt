package com.civciv.app.data.repository

import androidx.paging.PagingSource
import com.civciv.app.database.entities.status.StatusWithAccount

interface TimelineRepository {
    fun getTimeline(): PagingSource<Int, StatusWithAccount>
}
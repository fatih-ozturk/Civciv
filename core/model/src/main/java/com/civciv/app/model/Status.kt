package com.civciv.app.model

data class Status(
    val statusId: String,
    val createdAt: Long,
    val editedAt: Long?,
    val reblogsCount: Int,
    val favouritesCount: Int,
    val repliesCount: Int,
    val reblogged: Boolean,
    val bookmarked: Boolean,
    val favourited: Boolean,
    val sensitive: Boolean,
)
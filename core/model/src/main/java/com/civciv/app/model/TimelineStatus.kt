package com.civciv.app.model

data class TimelineStatus(
    val status: Status,
    val account: Account,
    val reblogAccount: Account?,
)
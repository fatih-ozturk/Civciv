/*
 * Copyright 2024 Fatih OZTURK
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
package com.civciv.app.database.entities.status

import androidx.room.Entity
import androidx.room.Index

@Entity(
    primaryKeys = ["statusId", "statusAccountId"],
    tableName = "status",
    indices = [Index(value = ["statusId"], unique = true)],
)
data class StatusEntity(
    val statusId: String,
    val statusAccountId: String,
    val createdAt: Long,
    val editedAt: Long?,
    val reblogsCount: Int,
    val favouritesCount: Int,
    val repliesCount: Int,
    val reblogged: Boolean,
    val bookmarked: Boolean,
    val favourited: Boolean,
    val sensitive: Boolean,
    val reblogId: String?,
    val reblogAccountId: String?,
)

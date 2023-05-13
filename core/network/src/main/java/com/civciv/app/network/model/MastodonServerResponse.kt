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
package com.civciv.app.network.model

import com.google.gson.annotations.SerializedName

data class MastodonServerResponse(
    @SerializedName("domain") val domain: String,
    @SerializedName("languages") val languages: List<String?>,
    @SerializedName("blurhash") val blurhash: String,
    @SerializedName("approval_required") val approvalRequired: Boolean,
    @SerializedName("description") val description: String,
    @SerializedName("language") val language: String,
    @SerializedName("version") val version: String,
    @SerializedName("last_week_users") val lastWeekUsers: Int,
    @SerializedName("proxied_thumbnail") val proxiedThumbnail: String,
    @SerializedName("categories") val categories: List<String?>,
    @SerializedName("region") val region: String?,
    @SerializedName("total_users") val totalUsers: Int,
    @SerializedName("category") val category: String,
)

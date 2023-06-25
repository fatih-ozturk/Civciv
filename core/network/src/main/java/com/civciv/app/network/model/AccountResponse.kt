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

data class AccountResponse(
    @SerializedName("id") val id: String,
    @SerializedName("username") val localUsername: String,
    @SerializedName("acct") val username: String,
    @SerializedName("display_name") val displayName: String,
    @SerializedName("locked") val isLocked: Boolean,
    @SerializedName("bot") val isBot: Boolean,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("note") val note: String,
    @SerializedName("url") val url: String,
    @SerializedName("avatar") val avatar: String,
)

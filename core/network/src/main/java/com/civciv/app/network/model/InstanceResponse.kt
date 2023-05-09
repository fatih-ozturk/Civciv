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

data class InstanceResponse(
    @SerializedName("uri") val uri: String,
    @SerializedName("title") val title: String,
    @SerializedName("version") val version: String,
    @SerializedName("stats") val stats: InstanceStatsResponse,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("registrations") val registrations: Boolean,
    @SerializedName("approval_required") val approvalRequired: Boolean,
    @SerializedName("invites_enabled") val invitesEnabled: String,
    @SerializedName("configuration") val configuration: ConfigurationResponse,
    @SerializedName("rules") val rules: List<InstanceRulesResponse>?,
)

data class InstanceRulesResponse(
    @SerializedName("id") val id: String,
    @SerializedName("text") val text: String,
)

data class InstanceStatsResponse(
    @SerializedName("user_count") val userCount: String,
    @SerializedName("status_count") val statusCount: String,
    @SerializedName("domain_count") val domainCount: String,
)

data class ConfigurationResponse(
    @SerializedName("statuses") val statuses: StatusesResponse,
)

data class StatusesResponse(
    @SerializedName("max_characters") val maxCharacters: Int,
    @SerializedName("max_media_attachments") val maxMediaAttachments: Int,
    @SerializedName("characters_reserved_per_url") val charactersReservedPerUrl: Int,
)

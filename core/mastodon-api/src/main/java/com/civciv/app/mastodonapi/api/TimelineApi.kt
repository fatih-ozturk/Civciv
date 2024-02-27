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
package com.civciv.app.mastodonapi.api

import com.civciv.app.mastodonapi.core.getByPaths
import com.civciv.app.mastodonapi.core.json
import com.civciv.app.mastodonapi.core.parameterLimit
import com.civciv.app.mastodonapi.core.parameterMaxID
import com.civciv.app.mastodonapi.core.parameterMinID
import com.civciv.app.mastodonapi.core.parameterSinceId
import com.civciv.app.mastodonapi.model.StatusResponse
import io.ktor.client.HttpClient

class TimelineApi internal constructor(
    private val client: HttpClient,
) {
    suspend fun homeTimeline(
        maxId: String? = null,
        sinceId: String? = null,
        minId: String? = null,
        limit: Int? = null,
    ): List<StatusResponse> {
        return client.getByPaths("timelines/home") {
            json()
            parameterMaxID(maxId)
            parameterSinceId(sinceId)
            parameterMinID(minId)
            parameterLimit(limit)
        }
    }
}

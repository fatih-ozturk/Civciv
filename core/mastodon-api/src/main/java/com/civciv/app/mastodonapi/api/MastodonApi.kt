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
package com.civciv.app.mastodonapi.api

import com.civciv.app.mastodonapi.core.getByPaths
import com.civciv.app.mastodonapi.core.json
import com.civciv.app.mastodonapi.core.parameterCategory
import com.civciv.app.mastodonapi.core.parameterLanguage
import com.civciv.app.mastodonapi.model.MastodonCategoryResponse
import com.civciv.app.mastodonapi.model.MastodonLanguageResponse
import com.civciv.app.mastodonapi.model.MastodonServerResponse
import io.ktor.client.HttpClient

class MastodonApi internal constructor(
    private val client: HttpClient,
) {

    suspend fun getMastodonCategories(
        language: String?,
    ): List<MastodonCategoryResponse> {
        return client.getByPaths("categories") {
            json()
            parameterLanguage(language)
        }
    }

    suspend fun getMastodonServers(
        language: String?,
        category: String?,
    ): List<MastodonServerResponse> {
        return client.getByPaths("servers") {
            json()
            parameterLanguage(language)
            parameterCategory(category)
        }
    }

    suspend fun getMastodonLanguages(): List<MastodonLanguageResponse> {
        return client.getByPaths("languages") {
            json()
        }
    }
}

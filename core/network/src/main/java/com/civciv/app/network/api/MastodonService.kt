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
package com.civciv.app.network.api

import com.civciv.app.network.model.MastodonCategoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MastodonService {

    @GET(MASTODON_CATEGORIES)
    fun getMastodonCategories(
        @Query("language") language: String,
    ): List<MastodonCategoryResponse>

    @GET(MASTODON_SERVERS)
    fun getMastodonServers(
        @Query("language") language: String,
        @Query("category") category: String,
    )

    @GET(MASTODON_SERVER_LANGUAGES)
    fun getMastodonLanguages(
        @Query("language") language: String,
        @Query("category") category: String,
    )

    companion object {
        const val MASTODON_CATEGORIES = "https://api.joinmastodon.org/categories"
        const val MASTODON_SERVERS = "https://api.joinmastodon.org/servers"
        const val MASTODON_SERVER_LANGUAGES = "https://api.joinmastodon.org/languages"
    }
}

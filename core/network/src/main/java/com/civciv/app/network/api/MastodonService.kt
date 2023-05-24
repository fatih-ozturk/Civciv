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
import com.civciv.app.network.model.MastodonLanguageResponse
import com.civciv.app.network.model.MastodonServerResponse
import com.civciv.app.network.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MastodonService {

    @GET(MASTODON_CATEGORIES)
    suspend fun getMastodonCategories(
        @Header(Constants.DOMAIN_PLACEHOLDER) domain: String = MASTODON_BASE_URL,
        @Query("language") language: String?,
    ): List<MastodonCategoryResponse>

    @GET(MASTODON_SERVERS)
    suspend fun getMastodonServers(
        @Header(Constants.DOMAIN_PLACEHOLDER) domain: String = MASTODON_BASE_URL,
        @Query("language") language: String?,
        @Query("category") category: String?,
    ): List<MastodonServerResponse>

    @GET(MASTODON_SERVER_LANGUAGES)
    suspend fun getMastodonLanguages(
        @Header(Constants.DOMAIN_PLACEHOLDER) domain: String = MASTODON_BASE_URL,
    ): List<MastodonLanguageResponse>

    companion object {
        const val MASTODON_CATEGORIES = "categories"
        const val MASTODON_SERVERS = "servers"
        const val MASTODON_SERVER_LANGUAGES = "languages"
        const val MASTODON_BASE_URL = "api.joinmastodon.org"
    }
}

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
package com.civciv.app.data.repository

import com.civciv.app.model.MastodonCategory
import com.civciv.app.model.MastodonLanguage
import com.civciv.app.model.MastodonServer
import com.civciv.app.network.api.MastodonService
import com.civciv.app.network.model.MastodonCategoryResponse
import com.civciv.app.network.model.MastodonLanguageResponse
import com.civciv.app.network.model.MastodonServerResponse
import com.civciv.app.network.model.asExternalModel
import javax.inject.Inject

class MastodonRepositoryImpl @Inject constructor(
    private val mastodonService: MastodonService,
) : MastodonRepository {

    override suspend fun getServerList(
        language: String?,
        category: String?,
    ): Result<List<MastodonServer>> {
        return try {
            val serverList = mastodonService.getMastodonServers(language, category)
            Result.success(serverList.map(MastodonServerResponse::asExternalModel))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getLanguageList(): Result<List<MastodonLanguage>> {
        return try {
            val languageList = mastodonService.getMastodonLanguages()
            Result.success(languageList.map(MastodonLanguageResponse::asExternalModel))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCategoryList(
        language: String?,
    ): Result<List<MastodonCategory>> {
        return try {
            val categoryList = mastodonService.getMastodonCategories(language)
            Result.success(categoryList.map(MastodonCategoryResponse::asExternalModel))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

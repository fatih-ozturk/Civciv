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
package com.civciv.app.data.common.repository

import com.civciv.app.model.MastodonCategory
import com.civciv.app.model.MastodonLanguage
import com.civciv.app.model.MastodonServer
import com.civciv.app.network.api.MastodonService
import com.civciv.app.network.model.MastodonCategoryResponse
import com.civciv.app.network.model.MastodonLanguageResponse
import com.civciv.app.network.model.MastodonServerResponse
import com.civciv.app.network.model.asExternalModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MastodonRepositoryImpl @Inject constructor(
    private val mastodonService: MastodonService,
) : MastodonRepository {

    override suspend fun getServerList(
        language: String?,
        category: String?,
    ): List<MastodonServer> {
        val serverList =
            mastodonService.getMastodonServers(language = language, category = category)
        return serverList.map(MastodonServerResponse::asExternalModel)
    }

    override fun getLanguageList(): Flow<List<MastodonLanguage>> {
        return flow {
            val languageList = mastodonService.getMastodonLanguages()
            emit(languageList.map(MastodonLanguageResponse::asExternalModel))
        }
    }

    override suspend fun getCategoryList(
        language: String?,
    ): List<MastodonCategory> {
        val categoryList = mastodonService.getMastodonCategories(language = language)
        return categoryList.map(MastodonCategoryResponse::asExternalModel)
    }
}

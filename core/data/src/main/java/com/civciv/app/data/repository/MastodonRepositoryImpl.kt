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
package com.civciv.app.data.repository

import com.civciv.app.mastodonapi.api.MastodonApi
import com.civciv.app.mastodonapi.model.MastodonCategoryResponse
import com.civciv.app.mastodonapi.model.MastodonLanguageResponse
import com.civciv.app.mastodonapi.model.MastodonServerResponse
import com.civciv.app.model.MastodonCategory
import com.civciv.app.model.MastodonLanguage
import com.civciv.app.model.MastodonServer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MastodonRepositoryImpl @Inject constructor(
    private val mastodonService: MastodonApi,
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

    override suspend fun getCategoryList(language: String?): List<MastodonCategory> {
        val categoryList = mastodonService.getMastodonCategories(language = language)
        return categoryList.map(MastodonCategoryResponse::asExternalModel)
    }
}

// TODO Move later
fun MastodonServerResponse.asExternalModel(): MastodonServer {
    return MastodonServer(
        domain = domain,
        description = description,
        totalUser = totalUsers,
        languages = languages,
        categories = categories,
        thumbnail = proxiedThumbnail,
    )
}

fun MastodonLanguageResponse.asExternalModel(): MastodonLanguage {
    return MastodonLanguage(
        locale = locale,
        serversCount = serversCount,
    )
}

fun MastodonCategoryResponse.asExternalModel(): MastodonCategory = MastodonCategory(
    category = category,
    serversCount = serversCount,
)

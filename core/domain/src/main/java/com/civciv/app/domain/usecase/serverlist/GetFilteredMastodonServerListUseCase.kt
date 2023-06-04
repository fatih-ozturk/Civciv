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
package com.civciv.app.domain.usecase.serverlist

import com.civciv.app.model.MastodonServer
import javax.inject.Inject

class GetFilteredMastodonServerListUseCase @Inject constructor() {

    operator fun invoke(
        query: String?,
        serverList: List<MastodonServer>?,
    ): List<MastodonServer>? {
        return if (!query.isNullOrBlank()) {
            serverList?.filter { it.domain.contains(query) }.orEmpty()
        } else {
            null
        }
    }
}

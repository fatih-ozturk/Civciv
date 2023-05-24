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
package com.civciv.app.auth.serverlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.civciv.app.domain.usecase.serverlist.GetMastodonCategoryListUseCase
import com.civciv.app.domain.usecase.serverlist.GetMastodonLanguageListUseCase
import com.civciv.app.domain.usecase.serverlist.GetMastodonServerListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ServerListViewModel @Inject constructor(
    getMastodonServerListUseCase: GetMastodonServerListUseCase,
    getMastodonLanguageListUseCase: GetMastodonLanguageListUseCase,
    getMastodonCategoryListUseCase: GetMastodonCategoryListUseCase,
) : ViewModel() {

    private val selectedLanguage: MutableStateFlow<String?> = MutableStateFlow(null)

    private val selectedCategory: MutableStateFlow<String?> = MutableStateFlow(null)

    val uiState: StateFlow<ServerListUiState> = combine(
        selectedLanguage,
        selectedCategory,
        getMastodonLanguageListUseCase(),
    ) { selectedLanguage, selectedCategory, languageList ->

        val categoryList = getMastodonCategoryListUseCase(language = selectedLanguage)
        val serverList = getMastodonServerListUseCase(
            language = selectedLanguage,
            category = selectedCategory,
        )
        return@combine ServerListUiState.ServerList(serverList, languageList, categoryList)
    }
        .catch {
            Timber.e(it)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ServerListUiState.Loading,
        )
}

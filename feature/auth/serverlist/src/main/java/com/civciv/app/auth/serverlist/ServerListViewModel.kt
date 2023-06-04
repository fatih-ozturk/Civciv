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

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.civciv.app.domain.usecase.serverlist.GetFilteredMastodonServerListUseCase
import com.civciv.app.domain.usecase.serverlist.GetMastodonCategoryListUseCase
import com.civciv.app.domain.usecase.serverlist.GetMastodonLanguageListUseCase
import com.civciv.app.domain.usecase.serverlist.GetMastodonServerListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ServerListViewModel @Inject constructor(
    getMastodonServerListUseCase: GetMastodonServerListUseCase,
    getFilteredMastodonServerListUseCase: GetFilteredMastodonServerListUseCase,
    getMastodonLanguageListUseCase: GetMastodonLanguageListUseCase,
    getMastodonCategoryListUseCase: GetMastodonCategoryListUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val searchQuery = savedStateHandle.getStateFlow(SEARCH_QUERY, "")

    private val selectedLanguage: MutableStateFlow<String?> = MutableStateFlow(null)

    private val selectedCategory: MutableStateFlow<String?> = MutableStateFlow(null)

    val filteredServerList: StateFlow<ServerListSearchUiState> =
        searchQuery.flatMapLatest { searchQuery ->
            if (uiState.value is ServerListUiState.ServerList) {
                val serverList = (uiState.value as ServerListUiState.ServerList).serverList
                val filteredServerList =
                    getFilteredMastodonServerListUseCase(searchQuery, serverList)
                if (filteredServerList?.isEmpty() == true) {
                    flowOf(ServerListSearchUiState.NotFound)
                } else if (!filteredServerList.isNullOrEmpty()) {
                    flowOf(ServerListSearchUiState.SearchResult(searchResult = filteredServerList))
                } else {
                    flowOf(ServerListSearchUiState.Empty)
                }
            } else {
                flowOf(ServerListSearchUiState.Empty)
            }
        }
            .catch {
                Timber.e(it)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ServerListSearchUiState.Empty,
            )

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

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }
}

private const val SEARCH_QUERY = "searchQuery"

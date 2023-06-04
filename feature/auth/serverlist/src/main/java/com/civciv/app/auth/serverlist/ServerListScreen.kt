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

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.civciv.app.ui.ext.ifTrue

@Composable
internal fun ServerListScreen(
    onBackClicked: () -> Unit,
    onServerClicked: (domain: String) -> Unit,
    viewModel: ServerListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val filteredServerList by viewModel.filteredServerList.collectAsStateWithLifecycle()

    ServerList(
        uiState = uiState,
        searchQuery = searchQuery,
        onSearchQueryChanged = viewModel::onSearchQueryChanged,
        serverListSearchUiState = filteredServerList,
        onBackClicked = onBackClicked,
        onServerClicked = onServerClicked,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ServerList(
    uiState: ServerListUiState,
    serverListSearchUiState: ServerListSearchUiState,
    searchQuery: String = "",
    onSearchQueryChanged: (String) -> Unit = {},
    onBackClicked: () -> Unit = {},
    onServerClicked: (domain: String) -> Unit = {},
) {
    val focusManager = LocalFocusManager.current

    var active by rememberSaveable { mutableStateOf(false) }

    Column(Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .ifTrue(!active) { padding(horizontal = 16.dp) },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if (!active) {
                Icon(
                    modifier = Modifier
                        .clickable(onClick = onBackClicked),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                )
            }
            Box(
                Modifier
                    .semantics { isTraversalGroup = true }
                    .zIndex(1f)
                    .fillMaxWidth(),
            ) {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .ifTrue(!active) { padding(bottom = 16.dp) }
                        .align(Alignment.TopCenter)
                        .animateContentSize(),
                    query = searchQuery,
                    enabled = uiState is ServerListUiState.ServerList,
                    onQueryChange = onSearchQueryChanged,
                    onSearch = {
                        focusManager.clearFocus()
                        active = false
                    },
                    active = active,
                    onActiveChange = {
                        active = it
                        if (!active) focusManager.clearFocus()
                    },
                    placeholder = {
                        Text(
                            "Search",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    },
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.clickable {
                                when (active) {
                                    true -> {
                                        focusManager.clearFocus()
                                        active = false
                                        onSearchQueryChanged("")
                                    }

                                    false -> {
                                        // navigate back
                                    }
                                }
                            },
                            imageVector = if (active) {
                                Icons.Default.ArrowBack
                            } else {
                                Icons.Default.Search
                            },
                            contentDescription = null,
                        )
                    },
                ) {
                    when (serverListSearchUiState) {
                        ServerListSearchUiState.Empty -> {
                            Text(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .align(Alignment.CenterHorizontally),
                                text = "TYPE FOR SEARCH",
                                textAlign = TextAlign.Center,
                            )
                        }

                        is ServerListSearchUiState.SearchResult -> {
                            LazyColumn {
                                items(serverListSearchUiState.searchResult) {
                                    Column {
                                        Text(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(16.dp)
                                                .clickable {
                                                    onServerClicked(it.domain)
                                                },
                                            text = it.domain,
                                        )
                                        Divider()
                                    }
                                }
                            }
                        }

                        ServerListSearchUiState.NotFound -> {
                            Text(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .align(Alignment.CenterHorizontally),
                                text = "Not found any server for '$searchQuery'",
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }
        }
        when (uiState) {
            ServerListUiState.Error,
            ServerListUiState.Loading,
            -> Unit

            is ServerListUiState.ServerList -> {
                LazyColumn(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.Center,
                ) {
                    items(uiState.serverList) {
                        Column {
                            Text(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                                    .clickable {
                                        onServerClicked(it.domain)
                                    },
                                text = it.domain,
                            )
                            Divider()
                        }
                    }
                }
            }
        }
    }
}

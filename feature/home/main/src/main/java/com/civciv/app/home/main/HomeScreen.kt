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
package com.civciv.app.home.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PersonAddAlt1
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.civciv.app.model.Account
import kotlinx.collections.immutable.ImmutableList

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onAddAccount: () -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    when (val state = uiState) {
        is HomeUiState.Home -> HomeContent(
            account = state.account,
            accounts = state.accounts,
            onAddAccount = onAddAccount,
            onAccountChanged = viewModel::onAccountChanged,
            modifier = modifier,
        )

        HomeUiState.Loading -> {
            Box {
                Text(text = "Loading")
            }
        }
    }
}

@Composable
fun HomeContent(
    account: Account,
    accounts: ImmutableList<Account>,
    onAddAccount: () -> Unit,
    onAccountChanged: (accountId: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    Box {
        Column(
            modifier = modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars),
        ) {
            Text(text = "Current User = ${account.username}")

            Button(
                onClick = {
                    onAddAccount()
                },
            ) {
                Text(text = "Add Another Account")
            }

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Logout Current User")
            }

            Button(
                onClick = {
                    openBottomSheet = !openBottomSheet
                },
            ) {
                Text(text = "Account List")
            }
        }
    }
    if (openBottomSheet) {
        AccountListBottomSheet(
            accounts = accounts,
            onOpenBottomSheet = {
                openBottomSheet = false
            },
            onAddNewAccount = {
                onAddAccount()
            },
            onAccountClick = {
                onAccountChanged(it)
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountListBottomSheet(
    accounts: ImmutableList<Account>,
    onOpenBottomSheet: () -> Unit,
    onAddNewAccount: () -> Unit,
    onAccountClick: (accountId: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val bottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = { onOpenBottomSheet() },
        sheetState = bottomSheetState,
    ) {
        LazyColumn {
            items(accounts) {
                ListItem(
                    modifier = Modifier
                        .clickable {
                            onAccountClick(it.id)
                        },
                    headlineContent = { Text(it.username) },
                    leadingContent = {
                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = "Localized description",
                        )
                    },
                    trailingContent = {
                        if (it.isActive) {
                            Icon(
                                Icons.Default.Check,
                                contentDescription = "Localized description",
                            )
                        }
                    },
                )
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
            }
            item {
                ListItem(
                    modifier = Modifier.clickable { onAddNewAccount() },
                    headlineContent = { Text("Add new account") },
                    leadingContent = {
                        Icon(
                            Icons.Default.PersonAddAlt1,
                            contentDescription = "Localized description",
                        )
                    },
                )
            }
        }
    }
}

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

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToLoginGraph: () -> Unit,
) {
    val homeAuthUiState by viewModel.uiState.collectAsState()

    when (homeAuthUiState) {
        HomeAuthUiState.Authorized -> {
            Box(modifier = modifier) {
                Text(text = "HOME")
            }
        }
        HomeAuthUiState.Loading -> {
            Box(modifier = modifier) {
                Text(text = "LOADING")
            }
        }
        HomeAuthUiState.NotAuthorized -> {
            LaunchedEffect(homeAuthUiState) {
                onNavigateToLoginGraph()
            }
        }
    }
}

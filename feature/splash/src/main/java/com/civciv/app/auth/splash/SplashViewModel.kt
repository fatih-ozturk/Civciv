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
package com.civciv.app.auth.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.civciv.app.domain.usecase.GetActiveAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class SplashViewModel @Inject constructor(
    getActiveAccountUseCase: GetActiveAccountUseCase,
) : ViewModel() {

    val uiState: StateFlow<SplashUiState> = getActiveAccountUseCase()
        .map {
            if (it == null) {
                SplashUiState.NotLoggedIn
            } else {
                SplashUiState.LoggedIn
            }
        }
        .stateIn(
            scope = viewModelScope,
            initialValue = SplashUiState.Loading,
            started = SharingStarted.WhileSubscribed(5_000),
        )
}

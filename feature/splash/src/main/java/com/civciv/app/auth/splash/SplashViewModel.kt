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
import com.civciv.app.domain.usecase.GetAuthStateUseCase
import com.civciv.app.domain.usecase.UpdateCurrentUserUseCase
import com.civciv.app.model.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getAuthStateUseCase: GetAuthStateUseCase,
    private val updateCurrentUserUseCase: UpdateCurrentUserUseCase,
) : ViewModel() {

    val uiState: StateFlow<SplashUiState> = getAuthStateUseCase()
        .map { authState ->
            Timber.tag("AuthState").e(authState.name)
            when (authState) {
                AuthState.LOGGED_IN -> {
                    updateCurrentUserUseCase()
                    SplashUiState.Authorized
                }

                AuthState.LOGGED_OUT -> {
                    SplashUiState.NotAuthorized
                }
            }
        }
        .catch {
            emit(SplashUiState.NotAuthorized)
        }
        .stateIn(
            scope = viewModelScope,
            initialValue = SplashUiState.Loading,
            started = SharingStarted.Lazily,
        )
}

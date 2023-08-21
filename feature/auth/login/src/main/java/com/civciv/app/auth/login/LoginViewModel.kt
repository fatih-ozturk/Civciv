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
package com.civciv.app.auth.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.civciv.app.auth.login.navigation.LoginArgs
import com.civciv.app.domain.usecase.AddAccountCredentialsUseCase
import com.civciv.app.domain.usecase.GetApplicationCredentialsUseCase
import com.civciv.app.model.ApplicationCredentials
import com.civciv.app.model.auth.AuthorizationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getApplicationCredentialsUseCase: GetApplicationCredentialsUseCase,
    private val addAccountCredentialsUseCase: AddAccountCredentialsUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val loginArgs = LoginArgs(savedStateHandle)

    private val domain: String = loginArgs.domain

    private val _events: Channel<LoginEvent> = Channel(Channel.UNLIMITED)
    val events: Flow<LoginEvent> = _events.receiveAsFlow()

    private val _uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState

    init {
        initLoginScreenState()
    }

    private fun initLoginScreenState() {
        val uiState = LoginUiState.Login(domain = domain)
        _uiState.value = uiState
    }

    fun onLoginClick(domain: String) = viewModelScope.launch {
        _uiState.value = LoginUiState.Loading
        val appCredentials = getApplicationCredentialsUseCase(domain = domain)
        savedStateHandle[APP_CREDENTIALS_KEY] = appCredentials
        _events.send(LoginEvent.RedirectToAuth(appCredentials))
    }

    fun handleLoginResult(loginResult: AuthorizationResult) = viewModelScope.launch {
        val appCredentials = savedStateHandle.get<ApplicationCredentials>(APP_CREDENTIALS_KEY)
        if (loginResult.response.code.isEmpty() || appCredentials == null) {
            // Toast message
            _events.send(LoginEvent.FailedToLogin(appCredentials?.domain.toString()))
            _uiState.value = LoginUiState.Idle
        } else {
            addAccountCredentialsUseCase(loginResult.response, appCredentials)
            _events.send(LoginEvent.NavigateToHome)
        }
    }

    companion object {
        const val APP_CREDENTIALS_KEY = "APP_CREDENTIALS_KEY"
    }
}

sealed interface LoginUiState {
    data object Idle : LoginUiState
    data object Loading : LoginUiState
    data class Login(val domain: String) : LoginUiState
}

sealed interface LoginEvent {
    data class RedirectToAuth(
        val appCredentials: ApplicationCredentials,
    ) : LoginEvent

    data class FailedToLogin(
        val domain: String,
    ) : LoginEvent

    data object NavigateToHome : LoginEvent
}

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
import com.civciv.app.domain.usecase.AuthenticateAppUseCase
import com.civciv.app.model.ApplicationCredentials
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticateAppUseCase: AuthenticateAppUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val loginArgs = LoginArgs(savedStateHandle)

    val domain: String = loginArgs.domain

    private val _events: Channel<LoginEvent> = Channel(Channel.UNLIMITED)
    val events: Flow<LoginEvent> = _events.receiveAsFlow()

    fun onLoginClick(domain: String) {
        viewModelScope.launch {
            authenticateAppUseCase(domain = domain).fold(
                onSuccess = {
                    _events.send(
                        LoginEvent.RedirectToAuth(it),
                    )
                },
                onFailure = {
                },
            )
        }
    }
}

sealed interface LoginEvent {
    data class RedirectToAuth(
        val appCredentials: ApplicationCredentials,
    ) : LoginEvent
}

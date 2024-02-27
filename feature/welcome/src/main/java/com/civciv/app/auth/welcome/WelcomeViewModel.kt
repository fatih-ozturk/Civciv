/*
 * Copyright 2024 Fatih OZTURK
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
package com.civciv.app.auth.welcome

import androidx.lifecycle.viewModelScope
import com.civciv.app.auth.welcome.WelcomeContract.Event
import com.civciv.app.auth.welcome.WelcomeContract.State
import com.civciv.app.base.BaseViewModel
import com.civciv.app.domain.usecase.AddAccountCredentialsUseCase
import com.civciv.app.domain.usecase.GetApplicationCredentialsUseCase
import com.civciv.app.inputfield.ValidationResult
import com.civciv.app.model.auth.AuthorizationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
internal class WelcomeViewModel @Inject constructor(
    private val getApplicationCredentialsUseCase: GetApplicationCredentialsUseCase,
    private val addAccountCredentialsUseCase: AddAccountCredentialsUseCase,
    private val validator: WelcomeValidator,
) : BaseViewModel<State, Event>() {

    override fun createInitialState(): State = State()

    override fun event(event: Event) {
        when (event) {
            is Event.HandleLoginResult -> handleLoginResult(event.loginResult)
            is Event.PerformLoginAction -> performLoginAction(event.domain)
            Event.NavigateToAuthConsumed -> appCredentialConsumed()
        }
    }

    private fun appCredentialConsumed() {
        setState {
            copy(
                navigateToAuth = false,
            )
        }
    }

    private fun performLoginAction(userDomain: String) = viewModelScope.launch {
        val domainResult = validator.validateDomain(userDomain)

        setState {
            copy(
                domain = domain.updateFromValidationResult(domainResult),
            )
        }

        if (domainResult is ValidationResult.Success) {
            setState {
                copy(isLoading = true)
            }
            val appCredentials = getApplicationCredentialsUseCase(domain = userDomain)
            setState {
                copy(
                    appCredentials = appCredentials,
                    navigateToAuth = true,
                )
            }
        }
    }

    private fun handleLoginResult(loginResult: AuthorizationResult) = viewModelScope.launch {
        val currentState = state.value

        if (loginResult.response.code.isEmpty() || currentState.appCredentials == null) {
            setState {
                copy(
                    isLoading = false,
                    error = WelcomeContract.Error.LoginCancelled,
                )
            }
            return@launch
        }

        val isSuccessful = addAccountCredentialsUseCase(
            authResult = loginResult.response,
            appCredentials = currentState.appCredentials,
        )

        if (isSuccessful) {
            setState {
                copy(
                    isLoading = false,
                    isLoginSuccessful = true,
                )
            }
        } else {
            setState {
                copy(
                    isLoading = false,
                    error = WelcomeContract.Error.FailedToLogin,
                )
            }
        }
    }
}

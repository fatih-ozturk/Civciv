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

import com.civciv.app.inputfield.StringInputField
import com.civciv.app.inputfield.ValidationResult
import com.civciv.app.model.ApplicationCredentials
import com.civciv.app.model.auth.AuthorizationResult

internal interface WelcomeContract {

    data class State(
        val appCredentials: ApplicationCredentials? = null,
        val domain: StringInputField = StringInputField(),
        val navigateToAuth: Boolean = false,
        val isLoginSuccessful: Boolean = false,
        val isLoading: Boolean = false,
        val error: Error? = null,
    )

    sealed interface Event {
        data class HandleLoginResult(val loginResult: AuthorizationResult) : Event
        data class PerformLoginAction(val domain: String) : Event
        data object NavigateToAuthConsumed : Event
    }

    interface Validator {
        fun validateDomain(domain: String): ValidationResult
    }

    sealed interface Error {
        data object FailedToLogin : Error
        data object LoginCancelled : Error
    }
}

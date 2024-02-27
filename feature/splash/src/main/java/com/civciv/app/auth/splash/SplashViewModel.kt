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
package com.civciv.app.auth.splash

import androidx.lifecycle.viewModelScope
import com.civciv.app.auth.splash.SplashContract.Event
import com.civciv.app.auth.splash.SplashContract.State
import com.civciv.app.base.BaseViewModel
import com.civciv.app.domain.usecase.GetAuthStateUseCase
import com.civciv.app.domain.usecase.UpdateCurrentUserUseCase
import com.civciv.app.model.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
internal class SplashViewModel @Inject constructor(
    private val getAuthStateUseCase: GetAuthStateUseCase,
    private val updateCurrentUserUseCase: UpdateCurrentUserUseCase,
) : BaseViewModel<State, Event>() {

    override fun createInitialState(): State = State()

    override fun event(event: Event) {
        when (event) {
            Event.CheckAuthorization -> handleOneTimeEvent(event, ::checkAuthState)
        }
    }

    private fun checkAuthState() = viewModelScope.launch {
        val currentAuthState = getAuthStateUseCase()
        when (currentAuthState) {
            AuthState.LOGGED_IN -> {
                updateCurrentUserUseCase()
                setState {
                    copy(
                        authState = State.AuthState.LOGGED_IN,
                    )
                }
            }

            AuthState.LOGGED_OUT -> {
                setState {
                    copy(
                        authState = State.AuthState.LOGGED_OUT,
                    )
                }
            }
        }
    }
}

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
package com.civciv.app

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _splashState: MutableStateFlow<MainActivitySplashState> =
        MutableStateFlow(MainActivitySplashState.Loading)
    val splashState: StateFlow<MainActivitySplashState> = _splashState

    fun hideSplashScreen() {
        _splashState.value = MainActivitySplashState.Success
    }
}

sealed interface MainActivitySplashState {
    object Loading : MainActivitySplashState
    object Success : MainActivitySplashState
}

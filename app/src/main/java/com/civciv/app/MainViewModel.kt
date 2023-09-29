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

import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class MainViewModel @AssistedInject constructor(
    @Assisted val initialState: MainState,
) : MavericksViewModel<MainState>(initialState = initialState) {

    init {
        hideSplashScreen()
    }

    private fun hideSplashScreen() {
        setState { copy(isLoading = false) }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<MainViewModel, MainState> {
        override fun create(state: MainState): MainViewModel
    }

    companion object :
        MavericksViewModelFactory<MainViewModel, MainState> by hiltMavericksViewModelFactory()
}

data class MainState(val isLoading: Boolean = true) : MavericksState

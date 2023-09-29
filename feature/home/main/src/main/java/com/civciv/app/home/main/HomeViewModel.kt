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

import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.civciv.app.domain.usecase.ChangeAccountUseCase
import com.civciv.app.domain.usecase.GetAuthorizedAccountsUseCase
import com.civciv.app.domain.usecase.GetCurrentAccountUseCase
import com.civciv.app.domain.usecase.LogoutAccountUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeViewModel @AssistedInject constructor(
    @Assisted initialState: HomeState,
    private val getCurrentAccountUseCase: GetCurrentAccountUseCase,
    private val getAuthorizedAccountsUseCase: GetAuthorizedAccountsUseCase,
    private val changeAccountUseCase: ChangeAccountUseCase,
    private val logoutAccountUseCase: LogoutAccountUseCase,
) : MavericksViewModel<HomeState>(initialState = initialState) {

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() = viewModelScope.launch {
        val currentAccount = getCurrentAccountUseCase()
        val authorizedAccounts = getAuthorizedAccountsUseCase()
        setState {
            copy(
                currentAccount = currentAccount,
                authorizedAccounts = authorizedAccounts.toImmutableList(),
            )
        }
    }

    fun onAccountChanged(accountId: String) = viewModelScope.launch {
        val job = async { changeAccountUseCase(accountId) }
        job.await()
        job.invokeOnCompletion { throwable ->
            if (throwable != null) {
                // TODO show error
                // or BaseViewModel.handleException(throwable)
            } else {
                setState { copy(isAccountChanged = true) }
            }
        }
    }

    fun onLogoutAccount() = viewModelScope.launch {
        val job = async { logoutAccountUseCase() }
        job.await()
        job.invokeOnCompletion { throwable ->
            if (throwable != null) {
                // TODO show error
                // or BaseViewModel.handleException(throwable)
            } else {
                setState { copy(isAccountLoggedOut = true) }
            }
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<HomeViewModel, HomeState> {
        override fun create(state: HomeState): HomeViewModel
    }

    companion object :
        MavericksViewModelFactory<HomeViewModel, HomeState> by hiltMavericksViewModelFactory()
}

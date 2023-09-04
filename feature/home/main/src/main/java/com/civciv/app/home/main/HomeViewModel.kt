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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.civciv.app.domain.usecase.ChangeAccountUseCase
import com.civciv.app.domain.usecase.GetAuthorizedAccountsUseCase
import com.civciv.app.domain.usecase.GetCurrentAccountUseCase
import com.civciv.app.domain.usecase.LogoutAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentAccountUseCase: GetCurrentAccountUseCase,
    private val getAuthorizedAccountsUseCase: GetAuthorizedAccountsUseCase,
    private val changeAccountUseCase: ChangeAccountUseCase,
    private val logoutAccountUseCase: LogoutAccountUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() = viewModelScope.launch {
        val currentAccount = getCurrentAccountUseCase()
        val authorizedAccounts = getAuthorizedAccountsUseCase()
        _uiState.emit(
            HomeUiState.Home(
                currentAccount,
                authorizedAccounts.toImmutableList(),
            ),
        )
    }

    fun onAccountChanged(accountId: String) = viewModelScope.launch {
        changeAccountUseCase(accountId)
    }

    fun onLogoutAccount() = viewModelScope.launch {
        logoutAccountUseCase()
    }
}

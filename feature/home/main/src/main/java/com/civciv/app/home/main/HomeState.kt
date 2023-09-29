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

import com.airbnb.mvrx.MavericksState
import com.civciv.app.model.Account
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class HomeState(
    val currentAccount: Account? = null,
    val authorizedAccounts: ImmutableList<Account> = persistentListOf(),
    val isLoading: Boolean = false,
    val isAccountChanged: Boolean = false,
    val isAccountLoggedOut: Boolean = false,
) : MavericksState

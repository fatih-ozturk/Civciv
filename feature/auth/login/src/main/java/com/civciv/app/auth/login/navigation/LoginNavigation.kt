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
package com.civciv.app.auth.login.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.civciv.app.auth.login.LoginScreen
import com.civciv.app.auth.login.navigation.LoginArgs.Companion.loginScreenDomainArg

const val loginScreenRoute = "login"

internal class LoginArgs(val domain: String) {

    constructor(savedStateHandle: SavedStateHandle) :
        this(checkNotNull<String>(savedStateHandle[loginScreenDomainArg]))

    companion object {
        const val loginScreenDomainArg = "domain"
    }
}

fun NavController.navigateToLogin(
    navOptions: NavOptions? = null,
    domain: String = "mastodon.social",
) {
    this.navigate("$loginScreenRoute/$domain", navOptions = navOptions)
}

fun NavGraphBuilder.loginScreen(
    onNavigateHome: () -> Unit,
    onBackClicked: () -> Unit,
) {
    composable(
        route = "$loginScreenRoute/{$loginScreenDomainArg}",
        arguments = listOf(
            navArgument(loginScreenDomainArg) {
                type = NavType.StringType
            },
        ),
    ) {
        LoginScreen(
            onNavigateHome = onNavigateHome,
            onBackClicked = onBackClicked,
        )
    }
}

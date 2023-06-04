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
package com.civciv.app.auth.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.civciv.app.auth.login.navigation.loginScreen
import com.civciv.app.auth.serverlist.navigation.serverListScreen
import com.civciv.app.auth.welcome.navigation.welcomeScreen
import com.civciv.app.auth.welcome.navigation.welcomeScreenRoute

const val authGraph = "authGraph"

fun NavGraphBuilder.authGraph(
    onLoginClicked: () -> Unit = { },
    onServerListClicked: () -> Unit = { },
    onBackClicked: () -> Unit = { },
    onServerClicked: (domain: String) -> Unit = { },
) {
    navigation(
        route = authGraph,
        startDestination = welcomeScreenRoute,
    ) {
        loginScreen()
        welcomeScreen(
            onLoginClicked = onLoginClicked,
            onServerListClicked = onServerListClicked,
        )
        serverListScreen(
            onBackClicked = onBackClicked,
            onServerClicked = onServerClicked,
        )
    }
}

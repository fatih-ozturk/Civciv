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
package com.civciv.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.civciv.app.home.navigation.navigateToHome
import com.civciv.app.ui.TopLevelDestination.HOME
import com.civciv.app.ui.TopLevelDestination.MESSAGES
import com.civciv.app.ui.TopLevelDestination.NOTIFICATION
import com.civciv.app.ui.TopLevelDestination.SEARCH

@Composable
fun rememberCivcivAppState(
    navController: NavHostController = rememberNavController(),
): CivcivAppState {
    return remember(navController) {
        CivcivAppState(navController)
    }
}

@Stable
class CivcivAppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    val shouldShowBottomBar: Boolean
        @Composable get() = currentDestination?.route in topLevelDestinations.map { it.name }

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            HOME -> navController.navigateToHome(topLevelNavOptions)
            SEARCH -> navController.navigateToHome(topLevelNavOptions)
            MESSAGES -> navController.navigateToHome(topLevelNavOptions)
            NOTIFICATION -> navController.navigateToHome(topLevelNavOptions)
        }
    }
}

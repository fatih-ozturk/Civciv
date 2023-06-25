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

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.civciv.app.auth.graph.authGraph
import com.civciv.app.auth.login.navigation.navigateToLogin
import com.civciv.app.auth.serverlist.navigation.navigateToServerList
import com.civciv.app.auth.splash.navigation.splashScreen
import com.civciv.app.auth.splash.navigation.splashScreenRoute
import com.civciv.app.auth.welcome.navigation.navigateToWelcome
import com.civciv.app.auth.welcome.navigation.welcomeScreenRoute
import com.civciv.app.home.graph.homeGraph
import com.civciv.app.home.main.navigation.navigateToHome
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalLayoutApi::class, ExperimentalComposeUiApi::class)
@Composable
fun CivcivApp(
    modifier: Modifier = Modifier,
    appState: CivcivAppState = rememberCivcivAppState(),
    onHideSplashScreen: () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Scaffold(
            modifier = Modifier.semantics { testTagsAsResourceId = true },
            contentColor = MaterialTheme.colorScheme.onBackground,
            bottomBar = {
                AnimatedVisibility(
                    visible = appState.shouldShowBottomBar,
                    enter = fadeIn(),
                    exit = fadeOut(),
                ) {
                    CivcivBottomNavigation(
                        destinations = appState.topLevelDestinations,
                        onNavigateToDestination = appState::navigateToTopLevelDestination,
                        currentDestination = appState.currentDestination,
                    )
                }
            },
        ) { padding ->
            LaunchedEffect(Unit) {
                appState.navController.currentBackStack.onEach { currentBackStack ->
                    Timber.tag("Fatih").e(currentBackStack.map { it.destination.route }.toString())
                }.collect()
            }

            CivcivNavHost(
                navController = appState.navController,
                modifier = modifier
                    .padding(padding)
                    .consumeWindowInsets(padding),
                onHideSplashScreen = onHideSplashScreen,
            )
        }
    }
}

@Composable
fun CivcivNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = splashScreenRoute,
    onHideSplashScreen: () -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        splashScreen(
            onNavigateToWelcomeScreen = {
                onHideSplashScreen()
                navController.navigateToWelcome(
                    navOptions {
                        popUpTo(splashScreenRoute) {
                            inclusive = true
                        }
                    },
                )
            },
            onNavigateToHomeScreen = {
                onHideSplashScreen()
                navController.navigateToHome(
                    navOptions {
                        popUpTo(splashScreenRoute) {
                            inclusive = true
                        }
                    },
                )
            },
        )
        homeGraph(
            onNavigateToWelcomeScreen = {
                navController.navigateToWelcome(
                    navOptions {
                        popUpTo(welcomeScreenRoute) {
                            inclusive = true
                        }
                    },
                )
            },
        )
        authGraph(
            onLoginClicked = {
                navController.navigateToLogin()
            },
            onServerListClicked = {
                navController.navigateToServerList()
            },
            onBackClicked = {
                navController.popBackStack()
            },
            onServerClicked = {
                navController.navigateToLogin(
                    navOptions = navOptions {
                        popUpTo(welcomeScreenRoute)
                    },
                    domain = it,
                )
            },
            onNavigateHome = {
                navController.navigateToHome(
                    navOptions {
                        popUpTo(authGraph) {
                            inclusive = true
                        }
                    },
                )
            },
        )
    }
}

/* ktlint-disable twitter-compose:unstable-collections */
@Composable
fun CivcivBottomNavigation(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = contentColorFor(MaterialTheme.colorScheme.surface),
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    Crossfade(targetState = selected, label = "") {
                        val icon = if (it) destination.selectedIcon else destination.unselectedIcon
                        Icon(
                            painter = painterResource(icon),
                            contentDescription = null,
                        )
                    }
                },
            )
        }
    }
}
/* ktlint-enable twitter-compose:unstable-collections */

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false

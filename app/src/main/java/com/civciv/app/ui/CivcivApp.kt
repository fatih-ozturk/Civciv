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
package com.civciv.app.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.civciv.app.auth.splash.navigation.splashScreen
import com.civciv.app.auth.splash.navigation.splashScreenRoute
import com.civciv.app.auth.welcome.navigation.navigateToWelcome
import com.civciv.app.auth.welcome.navigation.welcomeScreen
import com.civciv.app.designsystem.component.navbar.CivcivAnimatedNavBar
import com.civciv.app.designsystem.component.navbar.CivcivNavigationBarItem
import com.civciv.app.designsystem.theme.CivcivTheme
import com.civciv.app.designsystem.theme.NoRippleTheme
import com.civciv.app.home.navigation.homeScreen
import com.civciv.app.home.navigation.navigateToHome
import com.civciv.app.notification.navigation.notificationScreen
import com.civciv.app.profile.navigation.profileScreen
import com.civciv.app.search.navigation.searchScreen
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@SuppressLint("RestrictedApi")
@Composable
fun CivcivApp(
    modifier: Modifier = Modifier,
    appState: CivcivAppState = rememberCivcivAppState(),
    hideSplashScreen: () -> Unit = {},
) {
    LaunchedEffect(Unit) {
        appState.navController.currentBackStack
            .onEach { currentBackStack ->
                val backStack =
                    currentBackStack
                        .mapNotNull { it.destination.route }
                Timber.tag("CivcivApp currentBackStack").e(backStack.toString())
            }.collect()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Scaffold(
            modifier = Modifier,
            contentColor = MaterialTheme.colorScheme.onBackground,
            bottomBar = {
                if (appState.shouldShowBottomBar) {
                    CivcivBottomNavigation(
                        destinations = appState.topLevelDestinations.toImmutableList(),
                        onNavigateToDestination = appState::navigateToTopLevelDestination,
                        currentDestination = appState.currentDestination,
                    )
                }
            },
        ) { padding ->
            CivcivNavHost(
                navController = appState.navController,
                modifier =
                modifier
                    .fillMaxSize()
                    .padding(padding)
                    .consumeWindowInsets(padding)
                    .windowInsetsPadding(WindowInsets.safeContent),
                hideSplashScreen = hideSplashScreen,
            )
        }
    }
}

@Composable
fun CivcivNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    hideSplashScreen: () -> Unit = {},
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = splashScreenRoute,
    ) {
        splashScreen(
            onNavigateToHome = {
                navController.navigateToHome(
                    navOptions = navOptions {
                        popUpTo(
                            splashScreenRoute,
                            popUpToBuilder = {
                                inclusive = true
                            },
                        )
                    },
                )
                hideSplashScreen()
            },
            onNavigateToLoginGraph = {
                navController.navigateToWelcome(
                    navOptions = navOptions {
                        popUpTo(
                            splashScreenRoute,
                            popUpToBuilder = {
                                inclusive = true
                            },
                        )
                    },
                )
                hideSplashScreen()
            },
        )
        welcomeScreen()
        homeScreen()
        searchScreen()
        notificationScreen()
        profileScreen()
    }
}

@Composable
fun CivcivBottomNavigation(
    destinations: ImmutableList<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    val selectedDestinationIndex = destinations.indexOfFirst {
        currentDestination.isTopLevelDestinationInHierarchy(it)
    }
    CivcivAnimatedNavBar(
        modifier = modifier
            .background(CivcivTheme.colors.bgPrimary)
            .windowInsetsPadding(WindowInsets.navigationBars),
        selectedIndex = selectedDestinationIndex,
    ) {
        destinations.forEach { destination ->
            CivcivNavigationBarItem(onClick = {
                onNavigateToDestination(destination)
            }, icon = {
                CivcivIcon(
                    painter = painterResource(destination.selectedIcon),
                    contentDescription = stringResource(destination.label),
                )
            })
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false

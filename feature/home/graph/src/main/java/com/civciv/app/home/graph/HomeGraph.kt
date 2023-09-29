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
package com.civciv.app.home.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.civciv.app.home.main.navigation.homeMainScreenRoute
import com.civciv.app.home.main.navigation.homeScreen

const val homeGraph = "homeGraph"

fun NavController.navigateToHomeGraph(navOptions: NavOptions? = null) {
    this.navigate(homeGraph, navOptions)
}

fun NavGraphBuilder.homeGraph(
    onAddAccount: () -> Unit = {},
) {
    navigation(
        route = homeGraph,
        startDestination = homeMainScreenRoute,
    ) {
        homeScreen(
            onAddAccount = onAddAccount,
        )
    }
}

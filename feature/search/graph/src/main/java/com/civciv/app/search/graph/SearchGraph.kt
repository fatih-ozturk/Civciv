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
package com.civciv.app.search.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.civciv.app.search.main.navigation.searchScreen
import com.civciv.app.search.main.navigation.searchScreenRoute

const val searchGraph = "searchGraph"

fun NavController.navigateToSearchGraph(navOptions: NavOptions? = null) {
    this.navigate(searchGraph, navOptions)
}

fun NavGraphBuilder.searchGraph() {
    navigation(
        route = searchGraph,
        startDestination = searchScreenRoute,
    ) {
        searchScreen()
    }
}

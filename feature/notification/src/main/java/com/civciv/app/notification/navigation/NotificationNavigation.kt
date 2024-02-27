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
package com.civciv.app.notification.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.civciv.app.notification.NotificationScreen

const val notificationScreenRoute = "notification"

fun NavController.navigateToNotification(navOptions: NavOptions? = null) {
    this.navigate(notificationScreenRoute, navOptions)
}

fun NavGraphBuilder.notificationScreen() {
    composable(
        route = notificationScreenRoute,
    ) {
        NotificationScreen()
    }
}

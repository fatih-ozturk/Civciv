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

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.civciv.app.R

enum class TopLevelDestination(
    @DrawableRes val selectedIcon: Int,
    @StringRes val label: Int,
) {
    HOME(
        selectedIcon = R.drawable.ic_home,
        label = R.string.label_home,
    ),
    SEARCH(
        selectedIcon = R.drawable.ic_search,
        label = R.string.label_search,
    ),
    NOTIFICATION(
        selectedIcon = R.drawable.ic_notification,
        label = R.string.label_notification,
    ),
    PROFILE(
        selectedIcon = R.drawable.ic_profile,
        label = R.string.label_profile,
    ),
}

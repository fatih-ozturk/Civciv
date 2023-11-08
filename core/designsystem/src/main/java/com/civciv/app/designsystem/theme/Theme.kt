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
package com.civciv.app.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember

@Composable
fun CivcivTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) darkColors else lightColors
    ProvideCivcivResources(
        typography = civcivTypography,
        colors = colorScheme,
        shapes = civcivShapes,
    ) {
        MaterialTheme(
            colorScheme = colorScheme.asMaterial3Colors(),
            typography = mdTypography,
            shapes = mdShapes,
            content = content,
        )
    }
}

@Composable
fun ProvideCivcivResources(
    typography: CivcivTypography,
    colors: CivcivColors,
    shapes: CivcivShapes,
    content: @Composable () -> Unit,
) {
    val colorPalette = remember { colors }
    colorPalette.update(colors)
    CompositionLocalProvider(
        LocalCivcivTypographies provides typography,
        LocalCivcivColors provides colorPalette,
        LocalCivcivShapes provides shapes,
    ) {
        ProvideTextStyle(value = typography.displayMd, content = content)
    }
}

object CivcivTheme {
    val typography: CivcivTypography
        @Composable
        get() = LocalCivcivTypographies.current
    val colors: CivcivColors
        @Composable
        get() = LocalCivcivColors.current
    val shapes: CivcivShapes
        @Composable
        get() = LocalCivcivShapes.current
}
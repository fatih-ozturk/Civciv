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
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

@Composable
fun CivcivTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val shapes = CivCivTheme.shapes
    val colorScheme = when (darkTheme) {
        true -> darkColorScheme()
        false -> lightColorScheme()
    }
    val rememberedColorScheme = remember {
        // Explicitly creating a new object here so we don't mutate the initial [colorScheme]
        // provided, and overwrite the values set in it.
        colorScheme.copy()
    }.apply {
        updateColorSchemeFrom(colorScheme)
    }

    MaterialTheme(
        colorScheme = rememberedColorScheme.toMaterial3Colors(),
        typography = Typography,
        shapes = shapes.toMaterial3Shapes(),
    ) {
        CompositionLocalProvider(
            LocalColorScheme provides rememberedColorScheme,
            LocalShapes provides shapes,
            LocalContentColor provides rememberedColorScheme.background,
        ) {
            content()
        }
    }
}

object CivCivTheme {
    val colorScheme: CivcivColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val shapes: CivcivShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current
}

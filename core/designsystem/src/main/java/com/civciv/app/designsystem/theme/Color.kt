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

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color

internal object ColorLightTokens {
    val primary = Color(0XFFF8E74F)
    val primaryVariant = Color(0xFFB08C0E)
    val onPrimary = Color(0xDE000000)
    val surface = Color(0xFFD9D9D9)
    val onSurface = Color(0xFF1A1A1A)
    val background = Color(0xFFFFFFFF)
    val onBackground = Color(0xFF000000)
    val success = Color(0xFF66BB6A)
    val onSuccess = Color(0xDE000000)
    val warning = Color(0xFFFFA726)
    val onWarning = Color(0xDE000000)
    val error = Color(0xFFF44336)
    val onError = Color(0xFFFFFFFF)
    val info = Color(0xFF29B6F6)
    val onInfo = Color(0xDE000000)
}

internal object ColorDarkTokens {
    val primary = Color(0XFFF8E74F)
    val primaryVariant = Color(0xFFB08C0E)
    val onPrimary = Color(0xDE000000)
    val surface = Color(0xFF24252C)
    val onSurface = Color(0xFFEAEAEA)
    val background = Color(0xFF31323B)
    val onBackground = Color(0xFFEAEAEA)
    val success = Color(0xFF66BB6A)
    val onSuccess = Color(0xDE000000)
    val warning = Color(0xFFFFA726)
    val onWarning = Color(0xDE000000)
    val error = Color(0xFFF44336)
    val onError = Color(0xFFFFFFFF)
    val info = Color(0xFF29B6F6)
    val onInfo = Color(0xDE000000)
}

@Stable
class CivcivColors(
    primary: Color,
    primaryVariant: Color,
    onPrimary: Color,
    surface: Color,
    onSurface: Color,
    background: Color,
    onBackground: Color,
    success: Color,
    onSuccess: Color,
    warning: Color,
    onWarning: Color,
    error: Color,
    onError: Color,
    info: Color,
    onInfo: Color,
) {
    var primary by mutableStateOf(primary, structuralEqualityPolicy())
        internal set
    var primaryVariant by mutableStateOf(primaryVariant, structuralEqualityPolicy())
        internal set
    var onPrimary by mutableStateOf(onPrimary, structuralEqualityPolicy())
        internal set
    var surface by mutableStateOf(surface, structuralEqualityPolicy())
        internal set
    var onSurface by mutableStateOf(onSurface, structuralEqualityPolicy())
        internal set
    var background by mutableStateOf(background, structuralEqualityPolicy())
        internal set
    var onBackground by mutableStateOf(onBackground, structuralEqualityPolicy())
        internal set
    var success by mutableStateOf(success, structuralEqualityPolicy())
        internal set
    var onSuccess by mutableStateOf(onSuccess, structuralEqualityPolicy())
        internal set
    var warning by mutableStateOf(warning, structuralEqualityPolicy())
        internal set
    var onWarning by mutableStateOf(onWarning, structuralEqualityPolicy())
        internal set
    var error by mutableStateOf(error, structuralEqualityPolicy())
        internal set
    var onError by mutableStateOf(onError, structuralEqualityPolicy())
        internal set
    var info by mutableStateOf(info, structuralEqualityPolicy())
        internal set
    var onInfo by mutableStateOf(onInfo, structuralEqualityPolicy())
        internal set

    fun copy(
        primary: Color = this.primary,
        primaryVariant: Color = this.primaryVariant,
        onPrimary: Color = this.onPrimary,
        surface: Color = this.surface,
        onSurface: Color = this.onSurface,
        background: Color = this.background,
        onBackground: Color = this.onBackground,
        success: Color = this.success,
        onSuccess: Color = this.onSuccess,
        warning: Color = this.warning,
        onWarning: Color = this.onWarning,
        error: Color = this.error,
        onError: Color = this.onError,
        info: Color = this.info,
        onInfo: Color = this.onInfo,
    ): CivcivColors = CivcivColors(
        primary = primary,
        primaryVariant = primaryVariant,
        onPrimary = onPrimary,
        surface = surface,
        onSurface = onSurface,
        background = background,
        onBackground = onBackground,
        success = success,
        onSuccess = onSuccess,
        warning = warning,
        onWarning = onWarning,
        error = error,
        onError = onError,
        info = info,
        onInfo = onInfo,
    )
}

internal fun lightColorScheme(
    primary: Color = ColorLightTokens.primary,
    primaryVariant: Color = ColorLightTokens.primaryVariant,
    onPrimary: Color = ColorLightTokens.onPrimary,
    surface: Color = ColorLightTokens.surface,
    onSurface: Color = ColorLightTokens.onSurface,
    background: Color = ColorLightTokens.background,
    onBackground: Color = ColorLightTokens.onBackground,
    success: Color = ColorLightTokens.success,
    onSuccess: Color = ColorLightTokens.onSuccess,
    warning: Color = ColorLightTokens.warning,
    onWarning: Color = ColorLightTokens.onWarning,
    error: Color = ColorLightTokens.error,
    onError: Color = ColorLightTokens.onError,
    info: Color = ColorLightTokens.info,
    onInfo: Color = ColorLightTokens.onInfo,
): CivcivColors = CivcivColors(
    primary = primary,
    primaryVariant = primaryVariant,
    onPrimary = onPrimary,
    surface = surface,
    onSurface = onSurface,
    background = background,
    onBackground = onBackground,
    success = success,
    onSuccess = onSuccess,
    warning = warning,
    onWarning = onWarning,
    error = error,
    onError = onError,
    info = info,
    onInfo = onInfo,
)

internal fun darkColorScheme(
    primary: Color = ColorDarkTokens.primary,
    primaryVariant: Color = ColorDarkTokens.primaryVariant,
    onPrimary: Color = ColorDarkTokens.onPrimary,
    surface: Color = ColorDarkTokens.surface,
    onSurface: Color = ColorDarkTokens.onSurface,
    background: Color = ColorDarkTokens.background,
    onBackground: Color = ColorDarkTokens.onBackground,
    success: Color = ColorDarkTokens.success,
    onSuccess: Color = ColorDarkTokens.onSuccess,
    warning: Color = ColorDarkTokens.warning,
    onWarning: Color = ColorDarkTokens.onWarning,
    error: Color = ColorDarkTokens.error,
    onError: Color = ColorDarkTokens.onError,
    info: Color = ColorDarkTokens.info,
    onInfo: Color = ColorDarkTokens.onInfo,
): CivcivColors = CivcivColors(
    primary = primary,
    primaryVariant = primaryVariant,
    onPrimary = onPrimary,
    surface = surface,
    onSurface = onSurface,
    background = background,
    onBackground = onBackground,
    success = success,
    onSuccess = onSuccess,
    warning = warning,
    onWarning = onWarning,
    error = error,
    onError = onError,
    info = info,
    onInfo = onInfo,
)

internal fun CivcivColors.updateColorSchemeFrom(other: CivcivColors) {
    primary = other.primary
    primaryVariant = other.primaryVariant
    onPrimary = other.onPrimary
    surface = other.surface
    onSurface = other.onSurface
    background = other.background
    onBackground = other.onBackground
    success = other.success
    onSuccess = other.onSuccess
    warning = other.warning
    onWarning = other.onWarning
    error = other.error
    onError = other.onError
    info = other.info
    onInfo = other.onInfo
}

internal fun CivcivColors.toMaterial3Colors(): ColorScheme = ColorScheme(
    primary = primary,
    onPrimary = onPrimary,
    primaryContainer = Color.Magenta,
    onPrimaryContainer = Color.Magenta,
    inversePrimary = Color.Magenta,
    secondary = Color.Magenta,
    onSecondary = Color.Magenta,
    secondaryContainer = Color.Magenta,
    onSecondaryContainer = Color.Magenta,
    tertiary = info,
    onTertiary = onInfo,
    tertiaryContainer = Color.Magenta,
    onTertiaryContainer = Color.Magenta,
    background = background,
    onBackground = onBackground,
    surface = surface,
    onSurface = onSurface,
    surfaceVariant = Color.Magenta,
    onSurfaceVariant = Color.Magenta,
    surfaceTint = Color.Magenta,
    inverseSurface = Color.Magenta,
    inverseOnSurface = Color.Magenta,
    error = error,
    onError = onError,
    errorContainer = Color.Magenta,
    onErrorContainer = Color.Magenta,
    outline = Color.Magenta,
    outlineVariant = Color.Magenta,
    scrim = Color.Magenta,
)

internal val LocalColorScheme: ProvidableCompositionLocal<CivcivColors> =
    compositionLocalOf { lightColorScheme() }

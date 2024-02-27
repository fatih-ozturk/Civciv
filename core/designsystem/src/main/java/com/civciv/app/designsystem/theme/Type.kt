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
package com.civciv.app.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.civciv.app.designsystem.R

val civcivFonts =
    FontFamily(
        Font(R.font.inter_regular, FontWeight.Normal),
        Font(R.font.inter_medium, FontWeight.Medium),
        Font(R.font.inter_semibold, FontWeight.SemiBold),
        Font(R.font.inter_bold, FontWeight.Bold),
    )

data class CivcivTypography(
    val displayXl: TextStyle,
    val displayLg: TextStyle,
    val displayMd: TextStyle,
    val displaySm: TextStyle,
    val displayXs: TextStyle,
    val textXl: TextStyle,
    val textLg: TextStyle,
    val textMd: TextStyle,
    val textSm: TextStyle,
    val textXs: TextStyle,
)

internal val civcivTypography =
    CivcivTypography(
        displayXl =
        TextStyle(
            fontFamily = civcivFonts,
            fontSize = 60.sp,
            lineHeight = 72.sp,
            letterSpacing = (-1.2).sp,
        ),
        displayLg =
        TextStyle(
            fontFamily = civcivFonts,
            fontSize = 48.sp,
            lineHeight = 60.sp,
            letterSpacing = (-0.96).sp,
        ),
        displayMd =
        TextStyle(
            fontFamily = civcivFonts,
            fontSize = 36.sp,
            lineHeight = 44.sp,
            letterSpacing = (-0.72).sp,
        ),
        displaySm =
        TextStyle(
            fontFamily = civcivFonts,
            fontSize = 30.sp,
            lineHeight = 38.sp,
        ),
        displayXs =
        TextStyle(
            fontFamily = civcivFonts,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp,
        ),
        textXl =
        TextStyle(
            fontFamily = civcivFonts,
            fontSize = 20.sp,
            lineHeight = 30.sp,
        ),
        textLg =
        TextStyle(
            fontFamily = civcivFonts,
            fontSize = 18.sp,
            lineHeight = 28.sp,
        ),
        textMd =
        TextStyle(
            fontFamily = civcivFonts,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.1.sp,
        ),
        textSm =
        TextStyle(
            fontFamily = civcivFonts,
            fontSize = 14.sp,
            lineHeight = 20.sp,
        ),
        textXs =
        TextStyle(
            fontFamily = civcivFonts,
            fontSize = 12.sp,
            lineHeight = 18.sp,
        ),
    )

internal val LocalCivcivTypographies =
    compositionLocalOf<CivcivTypography> {
        error("No typography provided!")
    }

val mdTypography =
    Typography(
        displayLarge = civcivTypography.displayXl,
        displayMedium = civcivTypography.displayLg,
        displaySmall = civcivTypography.displayMd,
        headlineLarge = civcivTypography.displayMd,
        headlineMedium = civcivTypography.displaySm,
        headlineSmall = civcivTypography.displayXs,
        titleLarge = civcivTypography.displayXs,
        titleMedium = civcivTypography.textMd,
        titleSmall = civcivTypography.textSm,
        bodyLarge = civcivTypography.textMd,
        bodyMedium = civcivTypography.textSm,
        bodySmall = civcivTypography.textXs,
        labelLarge = civcivTypography.textSm,
        labelMedium = civcivTypography.textXs,
        labelSmall = civcivTypography.textXs,
    )

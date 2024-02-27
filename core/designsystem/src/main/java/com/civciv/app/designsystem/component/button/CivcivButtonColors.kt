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
package com.civciv.app.designsystem.component.button

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.civciv.app.designsystem.theme.CivcivTheme

@Immutable
class ButtonColors(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
)

object CivcivButtonColors {
    @Composable
    fun primary(
        containerColor: Color = CivcivTheme.colors.buttonPrimaryBg,
        contentColor: Color = CivcivTheme.colors.buttonPrimaryFg,
        disabledContainerColor: Color = CivcivTheme.colors.bgDisabled,
        disabledContentColor: Color = CivcivTheme.colors.fgDisabled,
    ): ButtonColors =
        ButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor,
        )

    @Composable
    fun secondaryGray(
        containerColor: Color = CivcivTheme.colors.buttonSecondaryBg,
        contentColor: Color = CivcivTheme.colors.buttonSecondaryFg,
        disabledContainerColor: Color = CivcivTheme.colors.bgPrimary,
        disabledContentColor: Color = CivcivTheme.colors.fgDisabled,
    ): ButtonColors =
        ButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor,
        )

    @Composable
    fun secondaryColor(
        containerColor: Color = CivcivTheme.colors.buttonSecondaryColorBg,
        contentColor: Color = CivcivTheme.colors.buttonSecondaryColorFg,
        disabledContainerColor: Color = CivcivTheme.colors.bgPrimary,
        disabledContentColor: Color = CivcivTheme.colors.fgDisabled,
    ): ButtonColors =
        ButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor,
        )

    @Composable
    fun tertiaryGray(
        containerColor: Color = Color.Transparent,
        contentColor: Color = CivcivTheme.colors.buttonTertiaryFg,
        disabledContainerColor: Color = CivcivTheme.colors.bgPrimary,
        disabledContentColor: Color = CivcivTheme.colors.fgDisabled,
    ): ButtonColors =
        ButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor,
        )

    @Composable
    fun tertiaryColor(
        containerColor: Color = Color.Transparent,
        contentColor: Color = CivcivTheme.colors.buttonTertiaryColorFg,
        disabledContainerColor: Color = CivcivTheme.colors.bgPrimary,
        disabledContentColor: Color = CivcivTheme.colors.fgDisabled,
    ): ButtonColors =
        ButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor,
        )

    @Composable
    fun primaryDestructive(
        containerColor: Color = CivcivTheme.colors.buttonPrimaryErrorBg,
        contentColor: Color = CivcivTheme.colors.fgWhite,
        disabledContainerColor: Color = CivcivTheme.colors.bgPrimary,
        disabledContentColor: Color = CivcivTheme.colors.fgDisabled,
    ): ButtonColors =
        ButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor,
        )

    @Composable
    fun secondaryDestructive(
        containerColor: Color = CivcivTheme.colors.buttonSecondaryErrorBg,
        contentColor: Color = CivcivTheme.colors.buttonSecondaryErrorFg,
        disabledContainerColor: Color = CivcivTheme.colors.bgPrimary,
        disabledContentColor: Color = CivcivTheme.colors.fgDisabled,
    ): ButtonColors =
        ButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor,
        )

    @Composable
    fun tertiaryDestructive(
        containerColor: Color = Color.Transparent,
        contentColor: Color = CivcivTheme.colors.buttonTertiaryErrorFg,
        disabledContainerColor: Color = CivcivTheme.colors.bgPrimary,
        disabledContentColor: Color = CivcivTheme.colors.fgDisabled,
    ): ButtonColors =
        ButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor,
        )
}

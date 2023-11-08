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
package com.civciv.app.designsystem.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.civciv.app.designsystem.theme.CivcivTheme

@Immutable
data class ButtonBorders(
    val stroke: BorderStroke? = null,
    val disabled: BorderStroke? = stroke,
)

object CivcivButtonBorders {
    @Composable
    fun secondaryGray(
        width: Dp = 1.dp,
        strokeColor: Color = CivcivTheme.colors.buttonSecondaryBorder,
        disabledColor: Color = CivcivTheme.colors.borderDisabledSubtle,
    ) = remember(
        width,
        strokeColor,
        disabledColor,
    ) {
        ButtonBorders(
            stroke = BorderStroke(width, strokeColor),
            disabled = BorderStroke(width, disabledColor),
        )
    }

    @Composable
    fun secondaryColor(
        width: Dp = 1.dp,
        strokeColor: Color = CivcivTheme.colors.buttonSecondaryColorBorder,
        disabledColor: Color = CivcivTheme.colors.borderDisabledSubtle,
    ) = remember(
        width,
        strokeColor,
        disabledColor,
    ) {
        ButtonBorders(
            stroke = BorderStroke(width, strokeColor),
            disabled = BorderStroke(width, disabledColor),
        )
    }

    @Composable
    fun secondaryDestructive(
        width: Dp = 1.dp,
        strokeColor: Color = CivcivTheme.colors.buttonSecondaryErrorBorder,
        disabledColor: Color = CivcivTheme.colors.borderDisabledSubtle,
    ) = remember(
        width,
        strokeColor,
        disabledColor,
    ) {
        ButtonBorders(
            stroke = BorderStroke(width, strokeColor),
            disabled = BorderStroke(width, disabledColor),
        )
    }

    @Composable
    fun none(): ButtonBorders = remember {
        ButtonBorders()
    }
}

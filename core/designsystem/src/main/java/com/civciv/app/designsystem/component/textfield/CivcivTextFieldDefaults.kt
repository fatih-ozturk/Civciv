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
package com.civciv.app.designsystem.component.textfield

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.civciv.app.designsystem.theme.CivcivTheme

object CivcivTextFieldDefaults {
    @Composable
    fun colors(
        textColor: Color = CivcivTheme.colors.textPrimary,
        placeholderTextColor: Color = CivcivTheme.colors.textPlaceholder,
        disabledPlaceholderTextColor: Color = CivcivTheme.colors.textDisabled,
        containerColor: Color = CivcivTheme.colors.bgPrimary,
        disabledContainerColor: Color = CivcivTheme.colors.bgDisabledSubtle,
        borderColor: Color = CivcivTheme.colors.borderPrimary,
        borderOuterColor: Color = CivcivTheme.colors.bgBrandSecondary,
        borderErrorOuterColor: Color = CivcivTheme.colors.bgErrorSecondary,
        focusedBorderColor: Color = CivcivTheme.colors.borderBrand,
        errorBorderColor: Color = CivcivTheme.colors.borderError,
        disabledBorderColor: Color = CivcivTheme.colors.borderDisabled,
        hintColor: Color = CivcivTheme.colors.textTertiary,
        errorHintColor: Color = CivcivTheme.colors.textErrorPrimary,
        trailingIconColor: Color = CivcivTheme.colors.fgQuinary,
        leadingIconColor: Color = CivcivTheme.colors.fgQuarterary,
        errorIconColor: Color = CivcivTheme.colors.fgErrorSecondary,
    ): CivcivTextFieldColors =
        remember(
            textColor,
            placeholderTextColor,
            disabledPlaceholderTextColor,
            containerColor,
            disabledContainerColor,
            borderColor,
            borderOuterColor,
            borderErrorOuterColor,
            focusedBorderColor,
            errorBorderColor,
            disabledBorderColor,
            hintColor,
            errorHintColor,
            trailingIconColor,
            leadingIconColor,
            errorIconColor,
        ) {
            CivcivTextFieldColors(
                textColor = textColor,
                placeholderTextColor = placeholderTextColor,
                disabledPlaceholderTextColor = disabledPlaceholderTextColor,
                containerColor = containerColor,
                disabledContainerColor = disabledContainerColor,
                borderColor = borderColor,
                borderOuterColor = borderOuterColor,
                borderErrorOuterColor = borderErrorOuterColor,
                focusedBorderColor = focusedBorderColor,
                errorBorderColor = errorBorderColor,
                disabledBorderColor = disabledBorderColor,
                hintColor = hintColor,
                errorHintColor = errorHintColor,
                trailingIconColor = trailingIconColor,
                leadingIconColor = leadingIconColor,
                errorIconColor = errorIconColor,
            )
        }
}

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
    ): CivcivTextFieldColors = remember(
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
        errorIconColor
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
            errorIconColor = errorIconColor
        )
    }
}


package com.civciv.app.designsystem.component.button

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.civciv.app.designsystem.theme.CivcivTheme

object CivcivButtonColors {

    @Composable
    fun primary(
        containerColor: Color = CivcivTheme.colors.buttonPrimaryBg,
        contentColor: Color = CivcivTheme.colors.buttonPrimaryFg,
        disabledContainerColor: Color = CivcivTheme.colors.bgDisabled,
        disabledContentColor: Color = CivcivTheme.colors.fgDisabled,
    ): ButtonColors = ButtonDefaults.buttonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

    @Composable
    fun secondaryGray(
        containerColor: Color = CivcivTheme.colors.buttonSecondaryBg,
        contentColor: Color = CivcivTheme.colors.buttonSecondaryFg,
        disabledContainerColor: Color = CivcivTheme.colors.bgPrimary,
        disabledContentColor: Color = CivcivTheme.colors.fgDisabled,
    ): ButtonColors = ButtonDefaults.buttonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

    @Composable
    fun secondaryColor(
        containerColor: Color = CivcivTheme.colors.buttonSecondaryColorBg,
        contentColor: Color = CivcivTheme.colors.buttonSecondaryColorFg,
        disabledContainerColor: Color = CivcivTheme.colors.bgPrimary,
        disabledContentColor: Color = CivcivTheme.colors.fgDisabled,
    ): ButtonColors = ButtonDefaults.buttonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

    @Composable
    fun tertiaryGray(
        containerColor: Color = Color.Transparent,
        contentColor: Color = CivcivTheme.colors.buttonTertiaryFg,
        disabledContainerColor: Color = CivcivTheme.colors.bgPrimary,
        disabledContentColor: Color = CivcivTheme.colors.fgDisabled,
    ): ButtonColors = ButtonDefaults.buttonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

    @Composable
    fun tertiaryColor(
        containerColor: Color = Color.Transparent,
        contentColor: Color = CivcivTheme.colors.buttonTertiaryColorFg,
        disabledContainerColor: Color = CivcivTheme.colors.bgPrimary,
        disabledContentColor: Color = CivcivTheme.colors.fgDisabled,
    ): ButtonColors = ButtonDefaults.buttonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

    @Composable
    fun primaryDestructive(
        containerColor: Color = CivcivTheme.colors.buttonPrimaryErrorBg,
        contentColor: Color = CivcivTheme.colors.fgWhite,
        disabledContainerColor: Color = CivcivTheme.colors.bgPrimary,
        disabledContentColor: Color = CivcivTheme.colors.fgDisabled,
    ): ButtonColors = ButtonDefaults.buttonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

    @Composable
    fun secondaryDestructive(
        containerColor: Color = CivcivTheme.colors.buttonSecondaryErrorBg,
        contentColor: Color = CivcivTheme.colors.buttonSecondaryErrorFg,
        disabledContainerColor: Color = CivcivTheme.colors.bgPrimary,
        disabledContentColor: Color = CivcivTheme.colors.fgDisabled,
    ): ButtonColors = ButtonDefaults.buttonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

    @Composable
    fun tertiaryDestructive(
        containerColor: Color = Color.Transparent,
        contentColor: Color = CivcivTheme.colors.buttonTertiaryErrorFg,
        disabledContainerColor: Color = CivcivTheme.colors.bgPrimary,
        disabledContentColor: Color = CivcivTheme.colors.fgDisabled,
    ): ButtonColors = ButtonDefaults.buttonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )
}
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
        disabledColor
    ) {
        ButtonBorders(
            stroke = BorderStroke(width, strokeColor),
            disabled = BorderStroke(width, disabledColor)
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
        disabledColor
    ) {
        ButtonBorders(
            stroke = BorderStroke(width, strokeColor),
            disabled = BorderStroke(width, disabledColor)
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
        disabledColor
    ) {
        ButtonBorders(
            stroke = BorderStroke(width, strokeColor),
            disabled = BorderStroke(width, disabledColor)
        )
    }

    @Composable
    fun none(): ButtonBorders = remember {
        ButtonBorders()
    }
}
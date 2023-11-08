package com.civciv.app.designsystem.component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.civciv.app.designsystem.theme.CivcivTheme

@Immutable
data class ButtonSizes(
    val textStyle: TextStyle,
    val height: Dp,
    val iconSize: Dp,
    val iconPadding: Dp,
    val contentPadding: PaddingValues,
)

object CivcivButtonSizes {

    @Composable
    fun small(
        textStyle: TextStyle = CivcivTheme.typography.textSm.copy(
            fontWeight = FontWeight.SemiBold
        ),
        height: Dp = 36.dp,
        iconSize: Dp = 20.dp,
        iconPadding: Dp = 6.dp,
        contentPadding: PaddingValues = PaddingValues(
            vertical = 8.dp,
            horizontal = 12.dp
        ),
    ) = remember(
        textStyle,
        height,
        iconSize,
        iconPadding,
        contentPadding
    ) {
        ButtonSizes(
            textStyle = textStyle,
            height = height,
            iconSize = iconSize,
            iconPadding = iconPadding,
            contentPadding = contentPadding
        )
    }

    @Composable
    fun medium(
        textStyle: TextStyle = CivcivTheme.typography.textSm.copy(
            fontWeight = FontWeight.SemiBold
        ),
        height: Dp = 40.dp,
        iconSize: Dp = 20.dp,
        iconPadding: Dp = 6.dp,
        contentPadding: PaddingValues = PaddingValues(
            vertical = 10.dp,
            horizontal = 14.dp
        ),
    ) = remember(
        textStyle,
        height,
        iconSize,
        iconPadding,
        contentPadding
    ) {
        ButtonSizes(
            textStyle = textStyle,
            height = height,
            iconSize = iconSize,
            iconPadding = iconPadding,
            contentPadding = contentPadding
        )
    }

    @Composable
    fun large(
        textStyle: TextStyle = CivcivTheme.typography.textMd.copy(
            fontWeight = FontWeight.SemiBold
        ),
        height: Dp = 44.dp,
        iconSize: Dp = 20.dp,
        iconPadding: Dp = 8.dp,
        contentPadding: PaddingValues = PaddingValues(
            vertical = 12.dp,
            horizontal = 16.dp
        ),
    ) = remember(
        textStyle,
        height,
        iconSize,
        iconPadding,
        contentPadding
    ) {
        ButtonSizes(
            textStyle = textStyle,
            height = height,
            iconSize = iconSize,
            iconPadding = iconPadding,
            contentPadding = contentPadding
        )
    }

    @Composable
    fun xlarge(
        textStyle: TextStyle = CivcivTheme.typography.textMd.copy(
            fontWeight = FontWeight.SemiBold
        ),
        height: Dp = 48.dp,
        iconSize: Dp = 20.dp,
        iconPadding: Dp = 8.dp,
        contentPadding: PaddingValues = PaddingValues(
            vertical = 14.dp,
            horizontal = 18.dp
        ),
    ) = remember(
        textStyle,
        height,
        iconSize,
        iconPadding,
        contentPadding
    ) {
        ButtonSizes(
            textStyle = textStyle,
            height = height,
            iconSize = iconSize,
            iconPadding = iconPadding,
            contentPadding = contentPadding
        )
    }

    @Composable
    fun xxlarge(
        textStyle: TextStyle = CivcivTheme.typography.textMd.copy(
            fontWeight = FontWeight.SemiBold
        ),
        height: Dp = 44.dp,
        iconSize: Dp = 20.dp,
        iconPadding: Dp = 12.dp,
        contentPadding: PaddingValues = PaddingValues(
            vertical = 18.dp,
            horizontal = 22.dp
        ),
    ) = remember(
        textStyle,
        height,
        iconSize,
        iconPadding,
        contentPadding
    ) {
        ButtonSizes(
            textStyle = textStyle,
            height = height,
            iconSize = iconSize,
            iconPadding = iconPadding,
            contentPadding = contentPadding
        )
    }
}
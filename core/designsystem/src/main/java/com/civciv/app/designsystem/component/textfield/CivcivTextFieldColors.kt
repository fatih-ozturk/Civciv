package com.civciv.app.designsystem.component.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.civciv.app.designsystem.theme.CivcivTheme
import com.civciv.app.ui.ext.ifTrue

@Immutable
data class CivcivTextFieldColors(
    val textColor: Color,
    val placeholderTextColor: Color,
    val disabledPlaceholderTextColor: Color,
    val containerColor: Color,
    val disabledContainerColor: Color,
    val borderColor: Color,
    val borderOuterColor: Color,
    val borderErrorOuterColor: Color,
    val focusedBorderColor: Color,
    val errorBorderColor: Color,
    val disabledBorderColor: Color,
    val hintColor: Color,
    val errorHintColor: Color,
    val trailingIconColor: Color,
    val leadingIconColor: Color,
    val errorIconColor: Color,
) {

    @Composable
    internal fun borderModifier(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource,
    ): State<Modifier> {
        val focused by interactionSource.collectIsFocusedAsState()
        val targetValue = when {
            !enabled -> disabledBorderColor
            isError -> errorBorderColor
            focused -> focusedBorderColor
            else -> borderColor
        }
        val borderWidth = when {
            focused -> 3.dp
            else -> 1.dp
        }
        val outerBorderColor = when {
            !isError -> borderOuterColor
            else -> borderErrorOuterColor
        }
        val modifier = Modifier
            .ifTrue(focused) {
                border(
                    2.dp,
                    outerBorderColor,
                    CivcivTheme.shapes.radiusMd
                )
            }
            .border(
                borderWidth,
                targetValue,
                CivcivTheme.shapes.radiusMd
            )

        return rememberUpdatedState(modifier)
    }

    @Composable
    internal fun backgroundColors(
        enabled: Boolean,
    ): State<Color> {

        val targetValue = when {
            !enabled -> disabledContainerColor
            else -> containerColor
        }
        return rememberUpdatedState(targetValue)
    }

    @Composable
    internal fun hintTextColors(
        isError: Boolean,
    ): State<Color> {

        val targetValue = when {
            !isError -> hintColor
            else -> errorHintColor
        }
        return rememberUpdatedState(targetValue)
    }

    @Composable
    internal fun trailingIconColors(
        isError: Boolean,
    ): State<Color> {

        val targetValue = when {
            !isError -> trailingIconColor
            else -> errorIconColor
        }
        return rememberUpdatedState(targetValue)
    }

    @Composable
    internal fun placeholderTextColors(
        enabled: Boolean,
    ): State<Color> {

        val targetValue = when {
            !enabled -> disabledPlaceholderTextColor
            else -> placeholderTextColor
        }
        return rememberUpdatedState(targetValue)
    }
}
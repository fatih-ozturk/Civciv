package com.civciv.app.designsystem.component.textfield

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class CivcivTextFieldSizes(
    val contentPadding: PaddingValues,
    val textFieldHeight: Dp,
)

object CivcivTextFieldSizesDefaults {

    @Composable
    fun small(
        contentPadding: PaddingValues = PaddingValues(
            vertical = 8.dp,
            horizontal = 12.dp
        ),
        textFieldHeight: Dp = 40.dp,
    ) = remember {
        CivcivTextFieldSizes(
            contentPadding = contentPadding,
            textFieldHeight = textFieldHeight,
        )
    }

    @Composable
    fun medium(
        contentPadding: PaddingValues = PaddingValues(
            vertical = 12.dp,
            horizontal = 14.dp
        ),
        textFieldHeight: Dp = 44.dp,
    ) = remember {
        CivcivTextFieldSizes(
            contentPadding = contentPadding,
            textFieldHeight = textFieldHeight,
        )
    }
}
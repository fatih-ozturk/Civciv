package com.civciv.app.designsystem.component.button

import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope

@Composable
fun rememberCivcivButtonIndication(color: Color): Indication {
    val updatedContentColor by rememberUpdatedState(color)
    return remember { CivcivButtonIndication(contentColor = updatedContentColor) }
}

internal class CivcivButtonIndication(
    private val contentColor: Color,
) : Indication {
    private class DefaultIndicationInstance(
        private val color: Color,
        private val isPressed: State<Boolean>,
    ) : IndicationInstance {
        override fun ContentDrawScope.drawIndication() {
            drawContent()
            if (isPressed.value) {
                drawRect(
                    color = color.copy(alpha = 0.1f),
                    size = size,
                )
            }
        }
    }

    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        val isPressed = interactionSource.collectIsPressedAsState()
        return remember(contentColor, interactionSource) {
            DefaultIndicationInstance(contentColor, isPressed)
        }
    }
}

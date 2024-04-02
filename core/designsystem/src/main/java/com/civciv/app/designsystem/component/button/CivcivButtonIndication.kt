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

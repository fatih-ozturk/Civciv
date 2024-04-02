package com.civciv.app.designsystem.component.navbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout

@Composable
fun CivcivNavigationBarItem(
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
            ),
        contentAlignment = Alignment.Center,
    ) {
        Layout(content = icon, measurePolicy = { measurables, constraints ->
            require(measurables.isNotEmpty()) {
                "Must have at least one measurable"
            }
            val placeables =
                measurables.map { measurable ->
                    measurable.measure(constraints)
                }

            layout(
                width = constraints.maxWidth,
                height = constraints.maxHeight,
            ) {
                placeables.forEach { placeable ->
                    placeable.placeRelative(
                        x = (constraints.maxWidth - placeable.width) / 2,
                        y = (constraints.maxHeight - placeable.height) / 2,
                    )
                }
            }
        })
    }
}

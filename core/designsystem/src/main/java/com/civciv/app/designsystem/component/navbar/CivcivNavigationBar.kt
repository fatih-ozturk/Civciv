package com.civciv.app.designsystem.component.navbar

import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.isSpecified
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.civciv.app.designsystem.theme.CivcivTheme

@Composable
fun CivcivAnimatedNavBar(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    content: @Composable () -> Unit,
) {
    var itemOffsetList by remember { mutableStateOf(listOf<Offset>()) }

    val selectedItemOffset by remember(selectedIndex, itemOffsetList) {
        mutableStateOf(
            if (itemOffsetList.isEmpty()) {
                Offset.Infinite
            } else {
                itemOffsetList.getOrElse(selectedIndex) { Offset.Infinite }
            },
        )
    }

    val offsetAnimationState by animateOffsetAsState(
        targetValue = selectedItemOffset,
        animationSpec = spring(dampingRatio = 0.7f, stiffness = 700F),
        label = "",
    )

    Box(
        modifier = modifier
            .height(CivcivNavigationBarTokens.NavBarHeight)
            .shadow(CivcivNavigationBarTokens.NavBarShadow)
            .background(CivcivTheme.colors.bgPrimary),
    ) {
        if (offsetAnimationState.isSpecified) {
            Box(
                modifier = Modifier
                    .height(CivcivNavigationBarTokens.IndicatorHeight)
                    .width(CivcivNavigationBarTokens.IndicatorWidth)
                    .offset {
                        IntOffset(
                            x = offsetAnimationState.x.toInt(),
                            y = offsetAnimationState.y.toInt(),
                        )
                    }
                    .background(
                        color = CivcivTheme.colors.bgTertiary,
                        shape = CivcivTheme.shapes.radiusSm,
                    ),
            )
        }

        Layout(
            content = content,
            measurePolicy = { measurables, constraints ->
                require(measurables.isNotEmpty()) {
                    "It has to have at least one measurable"
                }
                val itemWidth = constraints.maxWidth / measurables.size
                val placeables =
                    measurables.map { measurable ->
                        measurable.measure(constraints.copy(maxWidth = itemWidth))
                    }

                val height = placeables.maxOf { it.height }

                layout(constraints.maxWidth, height) {
                    var navigationBarItemX = 0

                    val positions = arrayListOf<CivcivNavigationBarItemPosition>()

                    placeables.fastForEach { navigationBarItem ->
                        navigationBarItem.placeRelative(navigationBarItemX, 0)

                        positions.add(
                            element =
                            CivcivNavigationBarItemPosition(
                                x = navigationBarItemX +
                                    (navigationBarItem.width / 2f) -
                                    (CivcivNavigationBarTokens.IndicatorWidth / 2).toPx(),
                                y = (height / 2).toFloat() -
                                    (CivcivNavigationBarTokens.IndicatorHeight.toPx() / 2),
                            ),
                        )

                        navigationBarItemX += navigationBarItem.width
                    }
                    itemOffsetList =
                        positions.map { xCord ->
                            Offset(xCord.x, xCord.y)
                        }
                }
            },
        )
    }
}

internal object CivcivNavigationBarTokens {
    val IndicatorWidth = 62.0.dp
    val IndicatorHeight = 32.0.dp
    val NavBarHeight = 80.0.dp
    val NavBarShadow = 8.0.dp
}

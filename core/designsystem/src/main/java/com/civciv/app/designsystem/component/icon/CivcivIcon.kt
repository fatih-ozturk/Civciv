package com.civciv.app.designsystem.component.icon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import com.civciv.app.designsystem.theme.LocalCivcivContentColor

@Composable
fun CivcivIcon(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalCivcivContentColor.current,
) {
    val colorFilter =
        remember(tint) {
            if (tint == Color.Unspecified) null else ColorFilter.tint(tint)
        }
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier.defaultSizeFor(painter),
        colorFilter = colorFilter,
    )
}

private fun Modifier.defaultSizeFor(painter: Painter) = this.then(
    if (painter.intrinsicSize == Size.Unspecified || painter.intrinsicSize.isInfinite()) {
        Modifier.size(CivcivIconDefaults.IconSize)
    } else {
        Modifier
    },
)

private fun Size.isInfinite() = width.isInfinite() && height.isInfinite()

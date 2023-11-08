package com.civciv.app.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp

@Immutable
data class CivcivShapes(
    val radiusNone: RoundedCornerShape,
    val radiusXxs: RoundedCornerShape,
    val radiusXs: RoundedCornerShape,
    val radiusSm: RoundedCornerShape,
    val radiusMd: RoundedCornerShape,
    val radiusLg: RoundedCornerShape,
    val radiusXl: RoundedCornerShape,
    val radiusXxl: RoundedCornerShape,
    val radius3xl: RoundedCornerShape,
    val radius4xl: RoundedCornerShape,
    val radiusFull: RoundedCornerShape,
)

val civcivShapes = CivcivShapes(
    radiusNone = RoundedCornerShape(0.dp),
    radiusXxs = RoundedCornerShape(2.dp),
    radiusXs = RoundedCornerShape(4.dp),
    radiusSm = RoundedCornerShape(6.dp),
    radiusMd = RoundedCornerShape(8.dp),
    radiusLg = RoundedCornerShape(10.dp),
    radiusXl = RoundedCornerShape(12.dp),
    radiusXxl = RoundedCornerShape(16.dp),
    radius3xl = RoundedCornerShape(20.dp),
    radius4xl = RoundedCornerShape(24.dp),
    radiusFull = RoundedCornerShape(9999.dp),
)

internal val LocalCivcivShapes = compositionLocalOf<CivcivShapes> {
    error("No Shapes provided")
}

val mdShapes = Shapes(
    extraSmall = civcivShapes.radiusXs,
    small = civcivShapes.radiusSm,
    medium = civcivShapes.radiusMd,
    large = civcivShapes.radiusLg,
    extraLarge = civcivShapes.radiusXl
)


package com.civciv.app.designsystem.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp

internal object ShapeTokens {
    val CornerSmall = RoundedCornerShape(4.dp)
    val CornerMedium = RoundedCornerShape(8.dp)
    val CornerLarge = RoundedCornerShape(12.dp)
}

internal object ShapeDefaults {
    val Small: CornerBasedShape = ShapeTokens.CornerSmall
    val Medium: CornerBasedShape = ShapeTokens.CornerMedium
    val Large: CornerBasedShape = ShapeTokens.CornerLarge
}

@Immutable
class CivcivShapes(
    val small: CornerBasedShape = ShapeDefaults.Small,
    val medium: CornerBasedShape = ShapeDefaults.Medium,
    val large: CornerBasedShape = ShapeDefaults.Large,
)

internal fun CivcivShapes.toMaterial3Shapes(): Shapes = Shapes(
    small = small,
    medium = medium,
    large = large,
)

internal val LocalShapes: ProvidableCompositionLocal<CivcivShapes> =
    compositionLocalOf { CivcivShapes() }

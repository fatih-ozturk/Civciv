/*
 * Copyright 2023 Fatih OZTURK
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

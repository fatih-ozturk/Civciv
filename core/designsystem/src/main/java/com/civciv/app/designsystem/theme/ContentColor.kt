package com.civciv.app.designsystem.theme

import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.ui.graphics.Color

// public fun CivcivColors.contentColorFor(color: Color): Color =
//    when (color) {
//        bgPrimary -> fgPrimary
//        bgSecondary -> fgSecondary
//        bgTertiary -> fgTertiary
//        else -> Color.Unspecified
//    }

val LocalCivcivContentColor: ProvidableCompositionLocal<Color> = LocalContentColor

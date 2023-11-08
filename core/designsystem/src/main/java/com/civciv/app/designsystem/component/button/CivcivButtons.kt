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
package com.civciv.app.designsystem.component.button

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.civciv.app.designsystem.theme.CivcivTheme

object CivcivButtons {

    @Composable
    fun Primary(
        text: String,
        enabled: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        leftIcon: Painter? = null,
        rightIcon: Painter? = null,
        colors: ButtonColors = CivcivButtonColors.primary(),
        sizes: ButtonSizes = CivcivButtonSizes.medium(),
        borders: ButtonBorders = CivcivButtonBorders.none(),
    ) = CivcivButtonImpl(
        text = text,
        enabled = enabled,
        colors = colors,
        sizes = sizes,
        onClick = onClick,
        modifier = modifier,
        leftIcon = leftIcon,
        rightIcon = rightIcon,
        borders = borders
    )

    @Composable
    fun SecondaryGray(
        text: String,
        enabled: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        leftIcon: Painter? = null,
        rightIcon: Painter? = null,
        colors: ButtonColors = CivcivButtonColors.secondaryGray(),
        sizes: ButtonSizes = CivcivButtonSizes.medium(),
        borders: ButtonBorders = CivcivButtonBorders.secondaryGray(),
    ) = CivcivButtonImpl(
        text = text,
        enabled = enabled,
        colors = colors,
        sizes = sizes,
        onClick = onClick,
        modifier = modifier,
        leftIcon = leftIcon,
        rightIcon = rightIcon,
        borders = borders
    )

    @Composable
    fun SecondaryColor(
        text: String,
        enabled: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        leftIcon: Painter? = null,
        rightIcon: Painter? = null,
        colors: ButtonColors = CivcivButtonColors.secondaryColor(),
        sizes: ButtonSizes = CivcivButtonSizes.medium(),
        borders: ButtonBorders = CivcivButtonBorders.secondaryColor(),
    ) = CivcivButtonImpl(
        text = text,
        enabled = enabled,
        colors = colors,
        sizes = sizes,
        onClick = onClick,
        modifier = modifier,
        leftIcon = leftIcon,
        rightIcon = rightIcon,
        borders = borders
    )

    @Composable
    fun TertiaryGray(
        text: String,
        enabled: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        leftIcon: Painter? = null,
        rightIcon: Painter? = null,
        colors: ButtonColors = CivcivButtonColors.tertiaryGray(),
        sizes: ButtonSizes = CivcivButtonSizes.medium(),
        borders: ButtonBorders = CivcivButtonBorders.none(),
    ) = CivcivButtonImpl(
        text = text,
        enabled = enabled,
        colors = colors,
        sizes = sizes,
        onClick = onClick,
        modifier = modifier,
        leftIcon = leftIcon,
        rightIcon = rightIcon,
        borders = borders
    )

    @Composable
    fun TertiaryColor(
        text: String,
        enabled: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        leftIcon: Painter? = null,
        rightIcon: Painter? = null,
        colors: ButtonColors = CivcivButtonColors.tertiaryColor(),
        sizes: ButtonSizes = CivcivButtonSizes.medium(),
        borders: ButtonBorders = CivcivButtonBorders.none(),
    ) = CivcivButtonImpl(
        text = text,
        enabled = enabled,
        colors = colors,
        sizes = sizes,
        onClick = onClick,
        modifier = modifier,
        leftIcon = leftIcon,
        rightIcon = rightIcon,
        borders = borders
    )

    @Composable
    fun PrimaryDestructive(
        text: String,
        enabled: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        leftIcon: Painter? = null,
        rightIcon: Painter? = null,
        colors: ButtonColors = CivcivButtonColors.primaryDestructive(),
        sizes: ButtonSizes = CivcivButtonSizes.medium(),
        borders: ButtonBorders = CivcivButtonBorders.none(),
    ) = CivcivButtonImpl(
        text = text,
        enabled = enabled,
        colors = colors,
        sizes = sizes,
        onClick = onClick,
        modifier = modifier,
        leftIcon = leftIcon,
        rightIcon = rightIcon,
        borders = borders
    )

    @Composable
    fun SecondaryDestructive(
        text: String,
        enabled: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        leftIcon: Painter? = null,
        rightIcon: Painter? = null,
        colors: ButtonColors = CivcivButtonColors.secondaryDestructive(),
        sizes: ButtonSizes = CivcivButtonSizes.medium(),
        borders: ButtonBorders = CivcivButtonBorders.secondaryDestructive(),
    ) = CivcivButtonImpl(
        text = text,
        enabled = enabled,
        colors = colors,
        sizes = sizes,
        onClick = onClick,
        modifier = modifier,
        leftIcon = leftIcon,
        rightIcon = rightIcon,
        borders = borders
    )

    @Composable
    fun TertiaryDestructive(
        text: String,
        enabled: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        leftIcon: Painter? = null,
        rightIcon: Painter? = null,
        colors: ButtonColors = CivcivButtonColors.tertiaryDestructive(),
        sizes: ButtonSizes = CivcivButtonSizes.medium(),
        borders: ButtonBorders = CivcivButtonBorders.none(),
    ) = CivcivButtonImpl(
        text = text,
        enabled = enabled,
        colors = colors,
        sizes = sizes,
        onClick = onClick,
        modifier = modifier,
        leftIcon = leftIcon,
        rightIcon = rightIcon,
        borders = borders
    )

}

@Composable
internal fun CivcivButtonImpl(
    text: String,
    enabled: Boolean,
    colors: ButtonColors,
    sizes: ButtonSizes,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leftIcon: Painter? = null,
    rightIcon: Painter? = null,
    borders: ButtonBorders = CivcivButtonBorders.none(),
) {
    Button(
        enabled = enabled,
        modifier = modifier
            .height(sizes.height),
        colors = colors,
        shape = CivcivTheme.shapes.radiusMd,
        border = if (enabled) borders.stroke else borders.disabled,
        contentPadding = sizes.contentPadding,
        onClick = onClick
    ) {
        leftIcon?.let {
            Icon(
                modifier = Modifier.size(sizes.iconSize),
                painter = it,
                contentDescription = null,
                tint = if (enabled) colors.contentColor else colors.disabledContentColor
            )
            Spacer(modifier = Modifier.width(sizes.iconPadding))
        }
        Text(
            modifier = Modifier.weight(1f, fill = false),
            text = text,
            style = sizes.textStyle,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        rightIcon?.let {
            Spacer(modifier = Modifier.width(sizes.iconPadding))
            Icon(
                modifier = Modifier.size(sizes.iconSize),
                painter = it,
                contentDescription = null,
                tint = if (enabled) colors.contentColor else colors.disabledContentColor
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CivcivButtonsPreview() {
    CivcivTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(CivcivTheme.colors.bgPrimary)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CivcivButtons.Primary(
                text = "Button",
                enabled = true,
                onClick = {},
                leftIcon = rememberVectorPainter(image = Icons.Rounded.Add),
                rightIcon = rememberVectorPainter(image = Icons.Rounded.Add)
            )

            CivcivButtons.SecondaryGray(
                text = "Button",
                enabled = true,
                onClick = {},
                leftIcon = rememberVectorPainter(image = Icons.Rounded.Add),
                rightIcon = rememberVectorPainter(image = Icons.Rounded.Add)
            )

            CivcivButtons.SecondaryColor(
                text = "Button",
                enabled = true,
                onClick = {},
                leftIcon = rememberVectorPainter(image = Icons.Rounded.Add),
                rightIcon = rememberVectorPainter(image = Icons.Rounded.Add)
            )

            CivcivButtons.TertiaryGray(
                text = "Button",
                enabled = true,
                onClick = {},
                leftIcon = rememberVectorPainter(image = Icons.Rounded.Add),
                rightIcon = rememberVectorPainter(image = Icons.Rounded.Add)
            )

            CivcivButtons.TertiaryColor(
                text = "Button",
                enabled = true,
                onClick = {},
                leftIcon = rememberVectorPainter(image = Icons.Rounded.Add),
                rightIcon = rememberVectorPainter(image = Icons.Rounded.Add)
            )

            CivcivButtons.PrimaryDestructive(
                text = "Button",
                enabled = true,
                onClick = {},
                leftIcon = rememberVectorPainter(image = Icons.Rounded.Add),
                rightIcon = rememberVectorPainter(image = Icons.Rounded.Add)
            )

            CivcivButtons.SecondaryDestructive(
                text = "Button",
                enabled = true,
                onClick = {},
                leftIcon = rememberVectorPainter(image = Icons.Rounded.Add),
                rightIcon = rememberVectorPainter(image = Icons.Rounded.Add)
            )

            CivcivButtons.TertiaryDestructive(
                text = "Button",
                enabled = true,
                onClick = {},
                leftIcon = rememberVectorPainter(image = Icons.Rounded.Add),
                rightIcon = rememberVectorPainter(image = Icons.Rounded.Add)
            )
        }
    }
}
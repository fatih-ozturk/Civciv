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

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.civciv.app.designsystem.theme.CivcivTheme

object CivcivButtons {
    @Composable
    fun Primary(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
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
        borders = borders,
    )

    @Composable
    fun SecondaryGray(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
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
        borders = borders,
    )

    @Composable
    fun SecondaryColor(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
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
        borders = borders,
    )

    @Composable
    fun TertiaryGray(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
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
        borders = borders,
    )

    @Composable
    fun TertiaryColor(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
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
        borders = borders,
    )

    @Composable
    fun PrimaryDestructive(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
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
        borders = borders,
    )

    @Composable
    fun SecondaryDestructive(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
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
        borders = borders,
    )

    @Composable
    fun TertiaryDestructive(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
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
        borders = borders,
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
    val contentColor = if (enabled) colors.contentColor else colors.disabledContentColor
    val containerColor = if (enabled) colors.containerColor else colors.disabledContainerColor
    val border = if (enabled) borders.stroke else borders.disabled
    val colorFilter = ColorFilter.tint(
        if (enabled) colors.contentColor else colors.disabledContentColor,
    )
    val mergedStyle =
        sizes.textStyle.merge(
            TextStyle(
                color = contentColor,
            ),
        )
    Box(
        modifier =
        modifier
            .graphicsLayer(shape = CivcivTheme.shapes.radiusMd, clip = false)
            .then(
                if (border != null)
                    Modifier.border(
                        border,
                        shape = CivcivTheme.shapes.radiusMd,
                    )
                else
                    Modifier,
            )
            .clip(shape = CivcivTheme.shapes.radiusMd)
            .background(color = containerColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberCivcivButtonIndication(contentColor),
                enabled = enabled,
                onClick = onClick,
            ),
    ) {
        Row(
            Modifier
                .padding(sizes.contentPadding),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            leftIcon?.let {
                Image(
                    modifier = Modifier.size(sizes.iconSize),
                    painter = it,
                    contentDescription = null,
                    colorFilter = colorFilter,
                )
                Spacer(modifier = Modifier.width(sizes.iconPadding))
            }
            BasicText(
                modifier = Modifier.weight(1f, fill = false),
                text = text,
                style = mergedStyle,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
            rightIcon?.let {
                Spacer(modifier = Modifier.width(sizes.iconPadding))
                Image(
                    modifier = Modifier.size(sizes.iconSize),
                    painter = it,
                    contentDescription = null,
                    colorFilter = colorFilter,
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CivcivButtonsPreview() {
    CivcivTheme {
        Column(
            modifier =
            Modifier
                .fillMaxSize()
                .background(CivcivTheme.colors.bgPrimary)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            CivcivButtons.Primary(
                text = "Button",
                enabled = true,
                onClick = {},
            )

            CivcivButtons.SecondaryGray(
                text = "Button",
                enabled = true,
                onClick = {},
            )

            CivcivButtons.SecondaryColor(
                text = "Button",
                enabled = true,
                onClick = {},
            )

            CivcivButtons.TertiaryGray(
                text = "Button",
                enabled = true,
                onClick = {},
            )

            CivcivButtons.TertiaryColor(
                text = "Button",
                enabled = true,
                onClick = {},
            )

            CivcivButtons.PrimaryDestructive(
                text = "Button",
                onClick = {},
                enabled = true,
            )

            CivcivButtons.SecondaryDestructive(
                text = "Button",
                onClick = {},
                enabled = true,
            )

            CivcivButtons.TertiaryDestructive(
                text = "Button",
                enabled = true,
                onClick = {},
            )
        }
    }
}

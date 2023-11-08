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
package com.civciv.app.designsystem.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddLocation
import androidx.compose.material.icons.rounded.DonutLarge
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.civciv.app.designsystem.internal.CivcivPreviews
import com.civciv.app.designsystem.theme.CivcivTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun CivcivTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    colors: CivcivTextFieldColors = CivcivTextFieldDefaults.colors(),
    sizes: CivcivTextFieldSizes = CivcivTextFieldSizesDefaults.small(),
    label: String? = null,
    placeholder: String? = null,
    hint: String? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    isError: Boolean = false,
    readOnly: Boolean = false,
    textStyle: TextStyle = CivcivTheme.typography.textMd.copy(
        fontWeight = FontWeight.Normal
    ),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val borderModifier by colors.borderModifier(enabled, isError, interactionSource)
    val backgroundColors by colors.backgroundColors(enabled)
    val hintTextColors by colors.hintTextColors(isError)
    val trailingIconColors by colors.trailingIconColors(isError)
    val placeholderTextColors by colors.placeholderTextColors(enabled)

    Column(
        modifier = Modifier
            .background(CivcivTheme.colors.bgPrimary)
    ) {
        label?.let {
            Text(
                text = label,
                style = CivcivTheme.typography.textSm,
                fontWeight = FontWeight.Medium,
                color = CivcivTheme.colors.textSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        BasicTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            modifier = modifier
                .padding(top = 6.dp),
            enabled = enabled,
            readOnly = readOnly,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            visualTransformation = visualTransformation,
            onTextLayout = onTextLayout,
            interactionSource = interactionSource,
            textStyle = textStyle,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .height(sizes.textFieldHeight)
                        .fillMaxWidth()
                        .background(
                            backgroundColors,
                            CivcivTheme.shapes.radiusMd
                        )
                        .then(borderModifier)
                        .padding(sizes.contentPadding),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    leadingIcon?.let {
                        CompositionLocalProvider(
                            value = LocalContentColor provides colors.leadingIconColor,
                            content = leadingIcon
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        innerTextField()
                        if (value.isEmpty()) {
                            placeholder?.let {
                                Text(
                                    text = placeholder,
                                    style = CivcivTheme.typography.textMd,
                                    fontWeight = FontWeight.Normal,
                                    color = placeholderTextColors,
                                )
                            }
                        }
                    }
                    trailingIcon?.let {
                        Spacer(modifier = Modifier.width(8.dp))
                        CompositionLocalProvider(
                            value = LocalContentColor provides trailingIconColors,
                            content = trailingIcon
                        )
                    }
                }
            }
        )
        hint?.let {
            Text(
                modifier = Modifier.padding(top = 6.dp),
                text = hint,
                style = CivcivTheme.typography.textSm,
                fontWeight = FontWeight.Normal,
                color = hintTextColors,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

    }
}

@CivcivPreviews
@Composable
private fun CivcivTextFieldPreview() {
    CivcivTheme {
        Column(
            modifier = Modifier
                .background(CivcivTheme.colors.bgPrimary)
                .padding(16.dp)
        ) {
            CivcivTextField(
                label = "Email",
                placeholder = "Enter your email",
                hint = "This is a hint text to help user.",
                value = "",
                enabled = false,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(16.dp),
                        imageVector = Icons.Rounded.AddLocation,
                        contentDescription = null,
                    )
                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(20.dp),
                        imageVector = Icons.Rounded.DonutLarge,
                        contentDescription = null,
                    )
                }
            )

            Spacer(modifier = Modifier.height(30.dp))
            CivcivTextField(
                label = "Email",
                placeholder = "Enter your email",
                hint = "This is a hint text to help user.",
                value = "",
                enabled = true,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(16.dp),
                        imageVector = Icons.Rounded.AddLocation,
                        contentDescription = null,
                    )
                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(20.dp),
                        imageVector = Icons.Rounded.DonutLarge,
                        contentDescription = null,
                    )
                }
            )

            Spacer(modifier = Modifier.height(30.dp))
            CivcivTextField(
                label = "Email",
                placeholder = "Enter your email",
                hint = "This is a hint text to help user.",
                value = "",
                enabled = true,
                isError = true,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(16.dp),
                        imageVector = Icons.Rounded.AddLocation,
                        contentDescription = null,
                    )

                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(20.dp),
                        imageVector = Icons.Rounded.DonutLarge,
                        contentDescription = null,
                    )
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            CivcivTextField(
                label = "Email",
                placeholder = "Enter your email",
                hint = "This is a hint text to help user.",
                value = "",
                enabled = true,
                isError = false,
                interactionSource = object : MutableInteractionSource {
                    override val interactions: Flow<Interaction> = flowOf(FocusInteraction.Focus())
                    override suspend fun emit(interaction: Interaction) = Unit
                    override fun tryEmit(interaction: Interaction): Boolean = false
                },
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(16.dp),
                        imageVector = Icons.Rounded.AddLocation,
                        contentDescription = null,
                    )

                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(20.dp),
                        imageVector = Icons.Rounded.DonutLarge,
                        contentDescription = null,
                    )
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            CivcivTextField(
                label = "Email",
                placeholder = "Enter your email",
                hint = "This is a hint text to help user.",
                value = "",
                enabled = true,
                isError = true,
                interactionSource = object : MutableInteractionSource {
                    override val interactions: Flow<Interaction> = flowOf(FocusInteraction.Focus())
                    override suspend fun emit(interaction: Interaction) = Unit
                    override fun tryEmit(interaction: Interaction): Boolean = false
                },
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(16.dp),
                        imageVector = Icons.Rounded.AddLocation,
                        contentDescription = null,
                    )

                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(20.dp),
                        imageVector = Icons.Rounded.DonutLarge,
                        contentDescription = null,
                    )
                }
            )
        }
    }
}

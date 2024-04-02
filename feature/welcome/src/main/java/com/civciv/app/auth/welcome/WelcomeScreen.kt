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
package com.civciv.app.auth.welcome

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.civciv.app.auth.welcome.mapper.toResourceString
import com.civciv.app.commonui.R
import com.civciv.app.commonui.ext.CivcivPreview
import com.civciv.app.commonui.ext.restartActivity
import com.civciv.app.designsystem.component.button.CivcivButtons
import com.civciv.app.designsystem.component.textfield.CivcivTextField
import com.civciv.app.designsystem.theme.CivcivTheme

@Composable
internal fun WelcomeScreen(viewModel: WelcomeViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val loginLauncher = rememberLauncherForActivityResult(
        contract = LoginActivityResultContract(),
        onResult = {
            viewModel.event(WelcomeContract.Event.HandleLoginResult(it))
        },
    )

    LaunchedEffect(state.navigateToAuth) {
        if (state.navigateToAuth) {
            loginLauncher.launch(state.appCredentials)
            viewModel.event(WelcomeContract.Event.NavigateToAuthConsumed)
        }
    }

    LaunchedEffect(state.isLoginSuccessful) {
        if (state.isLoginSuccessful) {
            context.restartActivity()
        }
    }

    WelcomeScreenImpl(
        state = state,
        dispatch = viewModel::event,
    )
}

@Composable
private fun WelcomeScreenImpl(
    state: WelcomeContract.State,
    dispatch: (WelcomeContract.Event) -> Unit,
) {
    var domain by rememberSaveable { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {
                    keyboardController?.hide()
                    focusManager.clearFocus(true)
                },
            )
            .background(CivcivTheme.colors.bgPrimary),
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
        ) {
            Image(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .size(96.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null,
            )
            Text(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .align(Alignment.CenterHorizontally),
                text = "Welcome to Civciv",
                textAlign = TextAlign.Center,
                style = CivcivTheme.typography.displayXs,
                fontWeight = FontWeight.Bold,
                color = CivcivTheme.colors.textPrimary,
            )
            Text(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.CenterHorizontally),
                text = "Log in the mastodon server where you created your account",
                textAlign = TextAlign.Center,
                style = CivcivTheme.typography.textMd,
                fontWeight = FontWeight.Normal,
                color = CivcivTheme.colors.textTertiary,
            )
            CivcivTextField(
                modifier = Modifier.padding(top = 32.dp),
                value = domain,
                onValueChange = {
                    domain = it
                },
                label = "Server URL",
                placeholder = "androiddev.social",
                isError = state.domain.hasError(),
                hint = state.domain.error?.toResourceString(),
            )
            CivcivButtons.Primary(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .align(Alignment.CenterHorizontally),
                text = "Log in",
                onClick = {
                    dispatch(WelcomeContract.Event.PerformLoginAction(domain = domain))
                },
            )
            Text(
                text = "or",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally),
                style = CivcivTheme.typography.textMd,
                fontWeight = FontWeight.SemiBold,
                color = CivcivTheme.colors.textTertiary,
            )
            CivcivButtons.Primary(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally),
                text = "Join androiddev.social",
                onClick = {
                    // TODO()
                },
            )
            CivcivButtons.SecondaryGray(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally),
                text = "Pick my own server",
                onClick = {
                    // TODO()
                },
            )
        }

        if (state.isLoading) {
            Dialog(onDismissRequest = { }) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center),
                )
            }
        }
    }
}

@CivcivPreview
@Composable
private fun WelcomeScreenPreview(
    @PreviewParameter(WelcomeScreenPreviewProvider::class) state: WelcomeContract.State,
) {
    CivcivTheme {
        WelcomeScreenImpl(
            state = state,
            dispatch = {
            },
        )
    }
}

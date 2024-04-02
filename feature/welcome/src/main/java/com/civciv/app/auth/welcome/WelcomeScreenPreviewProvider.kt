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

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.civciv.app.domain.usecase.ValidateDomainUseCase
import com.civciv.app.inputfield.StringInputField

internal class WelcomeScreenPreviewProvider : PreviewParameterProvider<WelcomeContract.State> {
    override val values = sequenceOf(
        WelcomeContract.State(
            domain = StringInputField(value = "androiddev.social"),
        ),
        WelcomeContract.State(
            domain = StringInputField(
                value = "androiddevsocial",
                error = ValidateDomainUseCase.ValidateDomainError.InvalidDomainAddress,
            ),
        ),
        WelcomeContract.State(
            domain = StringInputField(
                error = ValidateDomainUseCase.ValidateDomainError.EmptyDomain,
            ),
        ),
    )
}

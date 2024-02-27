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
package com.civciv.app.auth.welcome.mapper

import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.civciv.app.auth.welcome.R
import com.civciv.app.domain.usecase.ValidateDomainUseCase.ValidateDomainError
import com.civciv.app.domain.usecase.ValidateDomainUseCase.ValidateDomainError.EmptyDomain
import com.civciv.app.domain.usecase.ValidateDomainUseCase.ValidateDomainError.InvalidDomainAddress
import com.civciv.app.inputfield.ValidationError

@Composable
internal fun ValidationError.toResourceString(): String {
    val resources = LocalContext.current.resources
    return when (this) {
        is ValidateDomainError -> toDomainErrorString(resources)
        else -> throw IllegalArgumentException(
            resources.getString(R.string.login_domain_unknown_error),
        )
    }
}

private fun ValidateDomainError.toDomainErrorString(resources: Resources): String {
    return when (this) {
        EmptyDomain -> resources.getString(R.string.login_domain_empty_error)
        InvalidDomainAddress -> resources.getString(R.string.login_domain_invalid_error)
    }
}

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
package com.civciv.app.domain.usecase

import com.civciv.app.inputfield.ValidationError
import com.civciv.app.inputfield.ValidationResult
import javax.inject.Inject

class ValidateDomainUseCase @Inject constructor() {
    operator fun invoke(domain: String): ValidationResult {
        val domainNameRegex = Regex("^(https?://)?(www\\.)?([\\w-]+\\.)+[\\w-]+/?$")
        return when {
            domain.isEmpty() -> ValidationResult.Failure(ValidateDomainError.EmptyDomain)
            domainNameRegex.matches(domain) -> ValidationResult.Success
            else -> ValidationResult.Failure(ValidateDomainError.InvalidDomainAddress)
        }
    }

    sealed interface ValidateDomainError : ValidationError {
        data object InvalidDomainAddress : ValidateDomainError

        data object EmptyDomain : ValidateDomainError
    }
}

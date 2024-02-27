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
package com.civciv.app.inputfield

class StringInputField(
    override val value: String = "",
    override val error: ValidationError? = null,
    override val isValid: Boolean = false,
) : InputField<String> {
    override fun updateValue(value: String): StringInputField {
        return StringInputField(
            value = value,
            error = null,
            isValid = false,
        )
    }

    override fun updateError(error: ValidationError?): StringInputField {
        return StringInputField(
            value = value,
            error = error,
            isValid = false,
        )
    }

    override fun updateValidity(isValid: Boolean): StringInputField {
        if (isValid == this.isValid) return this

        return StringInputField(
            value = value,
            error = null,
            isValid = isValid,
        )
    }

    override fun updateFromValidationResult(result: ValidationResult): StringInputField {
        return when (result) {
            is ValidationResult.Success ->
                StringInputField(
                    value = value,
                    error = null,
                    isValid = true,
                )

            is ValidationResult.Failure ->
                StringInputField(
                    value = value,
                    error = result.error,
                    isValid = false,
                )
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StringInputField

        if (value != other.value) return false
        if (error != other.error) return false
        return isValid == other.isValid
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + (error?.hashCode() ?: 0)
        result = 31 * result + isValid.hashCode()
        return result
    }

    override fun toString(): String {
        return "StringInputField(value='$value', error=$error, isValid=$isValid)"
    }
}

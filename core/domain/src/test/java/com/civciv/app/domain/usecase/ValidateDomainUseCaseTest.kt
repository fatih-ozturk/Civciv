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

import androidx.test.filters.SmallTest
import io.kotest.matchers.shouldBe
import kotlin.test.Test

@SmallTest
class ValidateDomainUseCaseTest {
    val validateDomainUseCase = ValidateDomainUseCase()

    @Test
    fun `verify domain`() {
        val domain = "androiddev.social"
        val result = validateDomainUseCase(domain)
        result shouldBe true
    }

    @Test
    fun `verify domain with login returns false`() {
        val domain = "androiddev.social/login"
        val result = validateDomainUseCase(domain)
        result shouldBe false
    }

    @Test
    fun `verify domain with www returns false`() {
        val domain = "www.androiddev.social"
        val result = validateDomainUseCase(domain)
        result shouldBe true
    }

    @Test
    fun `verify domain with https and www`() {
        val domain = "https://www.androiddev.social"
        val result = validateDomainUseCase(domain)
        result shouldBe true
    }

    @Test
    fun `verify domain with https`() {
        val domain = "https://androiddev.social"
        val result = validateDomainUseCase(domain)
        result shouldBe true
    }

    @Test
    fun `verify domain with https and login returns false`() {
        val domain = "https://androiddev.social/login"
        val result = validateDomainUseCase(domain)
        result shouldBe false
    }

    @Test
    fun `verify domain with https and login and query return false`() {
        val domain = "https://androiddev.social/login?query=1"
        val result = validateDomainUseCase(domain)
        result shouldBe false
    }
}

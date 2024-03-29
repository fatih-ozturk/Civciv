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
package com.civciv.app.mastodonapi.api

import com.civciv.app.mastodonapi.core.MastodonException
import com.civciv.app.mastodonapi.model.request.AccountRequest
import com.civciv.app.mastodonapi.utils.MastodonResponse
import com.civciv.app.mastodonapi.utils.buildMastodon
import com.civciv.app.testing.CoroutineTestRule
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.shouldBe
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class AccountApiTest {
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val mastodon =
        buildMastodon(
            responses =
            listOf(
                MastodonResponse("accounts/1", "account/account.json"),
                MastodonResponse(
                    "accounts/2",
                    "account/accountError.json",
                    HttpStatusCode(401, "This API requires an authenticated user"),
                ),
                MastodonResponse(
                    "accounts/3",
                    "account/accountNotFoundError.json",
                    HttpStatusCode(404, "Record not found"),
                ),
                MastodonResponse(
                    "accounts/4",
                    "account/accountSuspendedError.json",
                    HttpStatusCode(410, "Account is suspended"),
                ),
            ),
        )
    private val accountApi: AccountApi = mastodon.accountApi

    @Test
    fun getAccountById() {
        runTest {
            val response = accountApi.getAccount(AccountRequest(id = "1"))

            response.id shouldBe "1"
        }
    }

    @Test
    fun getAccountByIdGetAuthError() {
        runTest {
            val exception =
                shouldThrowExactly<MastodonException> {
                    accountApi.getAccount(AccountRequest(id = "2"))
                }

            exception.errorResponse.message shouldBe "This API requires an authenticated user"
            exception.errorResponse.statusCode shouldBe 401
        }
    }

    @Test
    fun getAccountByIdGetNotFoundError() {
        runTest {
            val exception =
                shouldThrowExactly<MastodonException> {
                    accountApi.getAccount(AccountRequest(id = "3"))
                }

            exception.errorResponse.message shouldBe "Record not found"
            exception.errorResponse.statusCode shouldBe 404
        }
    }

    @Test
    fun getAccountByIdSuspendedError() {
        runTest {
            val exception =
                shouldThrowExactly<MastodonException> {
                    accountApi.getAccount(AccountRequest(id = "4"))
                }

            exception.errorResponse.message shouldBe "Empty message"
            exception.errorResponse.statusCode shouldBe 410
        }
    }
}

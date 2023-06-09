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
package com.civciv.app.network.api

import com.civciv.app.network.model.AccountResponse
import com.civciv.app.network.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface AccountsService {

    @GET("/api/v1/accounts/{id}")
    fun getAccount(@Path("id") accountId: String): AccountResponse

    @GET("api/v1/accounts/verify_credentials")
    suspend fun verifyAccountCredentials(
        @Header(Constants.DOMAIN_PLACEHOLDER) domain: String? = null,
        @Header("Authorization") auth: String? = null,
    ): AccountResponse
}

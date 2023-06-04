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
package com.civciv.app.network.utils

import com.civciv.app.database.dao.AccountsDao
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber

class DomainInterceptor @Inject constructor(
    private val accountsDao: AccountsDao,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val builder = originalRequest.newBuilder()
        val domainIsDefined = originalRequest.header(Constants.DOMAIN_PLACEHOLDER)

        if (domainIsDefined != null) {
            /*
            * change retrofit default host to domain
            * we dont save domain until user logged in so we need to setup domain for auth
            */
            builder.url(originalRequest.url.newBuilder().host(domainIsDefined).build())
            builder.removeHeader(Constants.DOMAIN_PLACEHOLDER)
        } else {
            val currentAccount = accountsDao.getActiveAccount()
            currentAccount?.run {
                builder.url(originalRequest.url.newBuilder().host(domain).build())
                builder.addHeader("Authorization", "Bearer $accessToken")
            }
        }

        val newRequest: Request = builder.build()
        Timber.tag(this.toString()).i(newRequest.url.toString())
        Timber.tag(this.toString()).i(newRequest.headers["Authorization"].toString())
        return chain.proceed(newRequest)
    }
}

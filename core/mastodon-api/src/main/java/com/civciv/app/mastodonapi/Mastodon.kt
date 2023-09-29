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
package com.civciv.app.mastodonapi

import com.civciv.app.mastodonapi.api.AccountApi
import com.civciv.app.mastodonapi.api.AuthApi
import com.civciv.app.mastodonapi.api.MastodonApi
import com.civciv.app.mastodonapi.api.TimelineApi
import com.civciv.app.mastodonapi.core.HttpClientFactory
import com.civciv.app.mastodonapi.core.MastodonDsl
import io.ktor.client.HttpClient

@MastodonDsl
fun Mastodon(block: MastodonClientConfig.() -> Unit): Mastodon {
    val config = MastodonClientConfig().apply(block)
    return Mastodon(config)
}

class Mastodon internal constructor(private val config: MastodonClientConfig) {

    private val client by lazy {
        HttpClientFactory.buildHttpClient(
            config = config,
        )
    }

    val mastodonApi by buildApi(::MastodonApi)
    val accountApi by buildApi(::AccountApi)
    val authApi by buildApi(::AuthApi)
    val timelineApi by buildApi(::TimelineApi)

    private inline fun <T> buildApi(crossinline builder: (HttpClient) -> T) =
        lazy { builder(client) }
}

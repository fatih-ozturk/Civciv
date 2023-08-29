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

import com.civciv.app.mastodonapi.core.MastodonDsl
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

@MastodonDsl
class MastodonClientConfig {
    internal var httpClientConfigBlock: (HttpClientConfig<*>.() -> Unit)? = null
    internal var mastodonAuthCredentials: MastodonAuthCredentials? = null
    internal var httpClientBuilder: (() -> HttpClient)? = null

    fun userAuthentication(block: MastodonAuthCredentials.() -> Unit) {
        mastodonAuthCredentials = MastodonAuthCredentials().apply(block)
    }

    fun httpClientConfig(block: HttpClientConfig<*>.() -> Unit) {
        this.httpClientConfigBlock = block
    }

    fun <T : HttpClientEngineConfig> httpClient(
        engineFactory: HttpClientEngineFactory<T>,
        block: HttpClientConfig<T>.() -> Unit = {},
    ) {
        httpClientBuilder = {
            HttpClient(engineFactory, block)
        }
    }
}

@MastodonDsl
class MastodonAuthCredentials {

    internal var loadTokensProvider: (suspend () -> String?)? = null
    internal var loadDomainProvider: (() -> String?)? = null

    fun loadAccessToken(provider: suspend () -> String?) {
        loadTokensProvider = provider
    }

    fun loadDomain(provider: () -> String?) {
        loadDomainProvider = provider
    }
}

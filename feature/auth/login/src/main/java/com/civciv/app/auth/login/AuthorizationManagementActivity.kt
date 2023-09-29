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
package com.civciv.app.auth.login

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import com.civciv.app.model.ApplicationCredentials
import okhttp3.HttpUrl
import java.util.concurrent.atomic.AtomicBoolean

class AuthorizationManagementActivity : Activity() {

    private var isAuthStarted: AtomicBoolean = AtomicBoolean(false)

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(KEY_AUTH_STARTED, isAuthStarted.get())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        isAuthStarted.set(savedInstanceState.getBoolean(KEY_AUTH_STARTED, false))
    }

    override fun onResume() {
        super.onResume()
        if (!isAuthStarted.get()) {
            try {
                startAuth()
                isAuthStarted.set(true)
            } catch (exception: ActivityNotFoundException) {
                setResult(RESULT_CANCELED)
                finish()
            }
            return
        }

        if (intent?.data != null) {
            setResult(RESULT_OK, intent)
        } else {
            setResult(RESULT_CANCELED)
        }
        finish()
    }

    @Suppress("Deprecation")
    private fun startAuth() {
        val input: ApplicationCredentials? = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(KEY_AUTH_REQUEST, ApplicationCredentials::class.java)
        } else {
            intent.getParcelableExtra(KEY_AUTH_REQUEST)
        }

        if (input == null) {
            finishActivity(RESULT_CANCELED)
        } else {
            val uri = HttpUrl.Builder()
                .scheme("https")
                .host(input.domain)
                .addPathSegments("oauth/authorize")
                .addEncodedQueryParameter("client_id", input.clientId)
                .addEncodedQueryParameter("redirect_uri", input.redirectUri)
                .addEncodedQueryParameter("response_type", "code")
                .addEncodedQueryParameter("scope", input.scope)
                .build()
                .toString()
                .toUri()
            val customTabsBuilder = CustomTabsIntent.Builder()
                .setShowTitle(true)
                .build()
            customTabsBuilder.intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)

            customTabsBuilder.launchUrl(this, uri)
        }
    }

    companion object {
        private const val KEY_AUTH_REQUEST = "authRequest"
        private const val KEY_AUTH_STARTED = "authStarted"

        fun createIntent(
            context: Context,
            applicationCredentials: ApplicationCredentials,
        ): Intent {
            val intent = Intent(context, AuthorizationManagementActivity::class.java)
            intent.putExtra(KEY_AUTH_REQUEST, applicationCredentials)
            return intent
        }
    }
}

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
plugins {
    id("civciv.android.library")
    id("civciv.android.hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.civciv.app.mastodonapi"
}

dependencies {
    implementation(libs.kotlin.datetime)
    api(libs.kotlinx.serialization)

    implementation(libs.ktor.core)
    implementation(libs.ktor.auth)
    implementation(libs.ktor.logging)
    implementation(libs.ktor.serialization.gson)
    implementation(libs.ktor.serialization.json)
    implementation(libs.ktor.content.negotiation)
    implementation(libs.ktor.engine.android)
    implementation(libs.okhttp.okhttp)
    implementation(libs.okhttp.loggingInterceptor)
}
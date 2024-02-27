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
plugins {
    id("civciv.android.application")
    id("civciv.android.application.compose")
    id("civciv.android.hilt")
    id("civciv.jacoco")
    id("civciv.spotless")
    id("civciv.ktlint")
}

android {
    namespace = "com.civciv.app"

    defaultConfig {
        applicationId = "com.civciv.app"
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "com.civciv.app.testing.CivcivTestRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            versionNameSuffix = "-dev"
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            excludes.add("/META-INF/versions/9/previous-compilation-data.bin")
        }
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.commonUi)
    implementation(projects.core.designsystem)
    implementation(projects.core.database)
    implementation(projects.core.domain)
    implementation(projects.core.model)

    implementation(projects.feature.welcome)
    implementation(projects.feature.splash)
    implementation(projects.feature.home)
    implementation(projects.feature.search)
    implementation(projects.feature.notification)
    implementation(projects.feature.profile)

    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(kotlin("test"))
    debugImplementation(libs.androidx.compose.ui.testManifest)
    implementation(libs.timber)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.compose.runtime.tracing)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.window.manager)
    implementation(libs.androidx.profileinstaller)
    implementation(libs.kotlinx.coroutines.guava)
    implementation(libs.coil.kt)
    implementation(libs.kotlinx.collections.immutable)

    testImplementation(projects.core.testing)
    testImplementation(projects.core.network)
    testImplementation(libs.androidx.navigation.testing)
    testImplementation(libs.work.testing)
    testImplementation(kotlin("test"))
    kspTest(libs.hilt.compiler)
}

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
        }
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    implementation(project(":core:base"))
    implementation(project(":core:data"))
    implementation(project(":core:ui"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:database"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))

    implementation(project(":feature:splash"))
    implementation(project(":feature:home:graph"))
    implementation(project(":feature:home:main"))
    implementation(project(":feature:auth:graph"))
    implementation(project(":feature:auth:login"))
    implementation(project(":feature:auth:serverlist"))
    implementation(project(":feature:auth:welcome"))
    implementation(project(":feature:notification:graph"))
    implementation(project(":feature:notification:main"))
    implementation(project(":feature:profile:graph"))
    implementation(project(":feature:profile:main"))
    implementation(project(":feature:search:graph"))
    implementation(project(":feature:search:main"))

    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.work.ktx)

    implementation(libs.accompanist.navigation)

    implementation(libs.kotlinx.coroutines.android)
}

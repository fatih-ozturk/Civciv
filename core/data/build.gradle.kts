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
}

android {
    namespace = "com.civciv.app.data"
}

dependencies {
    implementation(project(":core:base"))
    implementation(project(":core:network"))
    implementation(project(":core:model"))
    implementation(project(":core:database"))

    implementation(libs.androidx.paging.runtime)
    implementation(libs.room.ktx)

    implementation(libs.androidx.datastore)
    implementation(libs.androidx.datastore.preferences)

    testImplementation(project(":core:testing"))
}

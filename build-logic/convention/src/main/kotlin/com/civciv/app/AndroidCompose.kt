package com.civciv.app

import com.android.build.api.dsl.CommonExtension
import com.civciv.app.utils.androidTestImplementation
import com.civciv.app.utils.buildComposeMetricsParameters
import com.civciv.app.utils.implementation
import com.civciv.app.utils.kotlinOptions
import com.civciv.app.utils.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *>,
) {

    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("composecompiler").get().toString()
        }

        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + buildComposeMetricsParameters()
        }

        dependencies {
            val bom = libs.findLibrary("compose-bom").get()
            implementation(platform(bom))
            androidTestImplementation(platform(bom))
        }
    }
}

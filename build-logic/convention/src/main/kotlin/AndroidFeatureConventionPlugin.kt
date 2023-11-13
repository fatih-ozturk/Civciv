import com.civciv.app.utils.androidTestImplementation
import com.civciv.app.utils.implementation
import com.civciv.app.utils.libs
import com.civciv.app.utils.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.project

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("civciv.android.library")
                apply("civciv.android.hilt")
                apply("civciv.jacoco")
                apply("civciv.spotless")
                apply("civciv.ktlint")
            }

            dependencies {
                implementation(project(":core:common"))
                implementation(project(":core:common-ui"))
                implementation(project(":core:designsystem"))
                implementation(project(":core:domain"))
                implementation(project(":core:model"))

                testImplementation(kotlin("test"))
                testImplementation(project(":core:testing"))
                androidTestImplementation(kotlin("test"))
                androidTestImplementation(project(":core:testing"))
                implementation(libs.findLibrary("airbnb.mavericks").get())
                implementation(libs.findLibrary("airbnb.mavericks.hilt").get())
                implementation(libs.findLibrary("airbnb.mavericks.compose").get())
                implementation(libs.findLibrary("coil.kt").get())
                implementation(libs.findLibrary("timber").get())
                implementation(libs.findLibrary("coil.kt.compose").get())

                implementation(libs.findLibrary("androidx.hilt.navigation.compose").get())
                implementation(libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                implementation(libs.findLibrary("androidx.lifecycle.viewModelCompose").get())

                implementation(libs.findLibrary("kotlinx.coroutines.android").get())
                implementation(libs.findLibrary("kotlinx.collections.immutable").get())
            }
        }
    }
}

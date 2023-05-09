
import com.civciv.app.utils.implementation
import com.civciv.app.utils.kapt
import com.civciv.app.utils.kaptAndroidTest
import com.civciv.app.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("dagger.hilt.android.plugin")
                apply("org.jetbrains.kotlin.kapt")
            }

            dependencies {
                implementation(libs.findLibrary("hilt.library").get())
                kapt(libs.findLibrary("hilt.compiler").get())
                kaptAndroidTest(libs.findLibrary("hilt.compiler").get())
            }
        }
    }
}

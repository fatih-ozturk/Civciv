import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.civciv.app.configureAndroidJacoco
import com.civciv.app.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension

class AndroidJacocoConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("jacoco")
            }
            val jacoco = extensions.getByType(JacocoPluginExtension::class.java)
            jacoco.apply {
                toolVersion = libs.findVersion("jacoco").get().toString()
            }
            extensions.findByType(ApplicationExtension::class.java)?.apply {
                configureAndroidJacoco(this, jacoco)
            }
            extensions.findByType(LibraryExtension::class.java)?.apply {
                configureAndroidJacoco(this, jacoco)
            }
        }
    }

}

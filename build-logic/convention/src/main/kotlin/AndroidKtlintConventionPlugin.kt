import com.civciv.app.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

class AndroidKtlintConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jlleitschuh.gradle.ktlint")
            }

            extensions.configure<KtlintExtension> {
                android.set(true)
                // not supported with ktlint 0.48+
                disabledRules.set(setOf("import-ordering", "no-wildcard-imports"))
                filter {
                    exclude("**/generated/**")
                    include("**/kotlin/**")
                }
                reporters {
                    reporter(ReporterType.HTML)
                }
            }

            dependencies {
                add("ktlintRuleset", libs.findLibrary("ktlint.compose").get())
            }
        }

    }
}

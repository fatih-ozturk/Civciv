package com.civciv.app

import com.android.build.api.dsl.CommonExtension
import groovy.xml.XmlSlurper
import groovy.xml.slurpersupport.NodeChild
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.tasks.JacocoReport
import java.io.File
import java.util.Locale
import kotlin.math.roundToInt

private val excludedFiles = mutableSetOf(
    "**/R.class",
    "**/R$*.class",
    "**/*\$ViewInjector*.*",
    "**/*\$ViewBinder*.*",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "**/*Factory*",
    "**/*_MembersInjector*",
    "**/*Module*",
    "**/*Component*",
    "**android**",
    "**/BR.class",
)

internal fun Project.configureAndroidJacoco(
    extension: CommonExtension<*, *, *, *>, jacoco: JacocoPluginExtension
) = afterEvaluate {

    val buildTypes = extension.buildTypes.map { type -> type.name }
    var productFlavors = extension.productFlavors.map { flavor -> flavor.name }

    if (productFlavors.isEmpty()) {
        productFlavors = productFlavors + ""
    }

    productFlavors.forEach { flavorName ->
        buildTypes.forEach { buildTypeName ->
            val sourceName: String
            val sourcePath: String

            if (flavorName.isEmpty()) {
                sourceName = buildTypeName
                sourcePath = buildTypeName
            } else {
                sourceName = "${flavorName}${
                    buildTypeName.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase((Locale.ENGLISH)) else it.toString()
                    }
                }"
                sourcePath = "${flavorName}/${buildTypeName}"
            }

            val testTaskName =
                "test${sourceName.replaceFirstChar { if (it.isLowerCase()) it.titlecase((Locale.ENGLISH)) else it.toString() }}UnitTest"

            registerCodeCoverageTask(
                jacoco = jacoco,
                testTaskName = testTaskName,
                sourceName = sourceName,
                sourcePath = sourcePath,
                flavorName = flavorName,
                buildTypeName = buildTypeName,
            )
        }
    }
}

private fun Project.registerCodeCoverageTask(
    jacoco: JacocoPluginExtension,
    testTaskName: String,
    sourceName: String,
    sourcePath: String,
    flavorName: String,
    buildTypeName: String
) {
    tasks.register("${testTaskName}Coverage", JacocoReport::class.java) {
        dependsOn(testTaskName)
        group = "Reporting"
        description =
            "Generate Jacoco coverage reports on the ${
                sourceName.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        (Locale.ENGLISH),
                    ) else it.toString()
                }
            } build."

        val javaDirectories = fileTree(
            "${project.buildDir}/intermediates/classes/${sourcePath}",
        ) { exclude(excludedFiles) }

        val kotlinDirectories = fileTree(
            "${project.buildDir}/tmp/kotlin-classes/${sourcePath}",
        ) { exclude(excludedFiles) }

        val coverageSrcDirectories = listOf(
            "src/main/java",
            "src/$flavorName/java",
            "src/$buildTypeName/java",
            "src/main/kotlin",
            "src/$flavorName/kotlin",
            "src/$buildTypeName/kotlin",
        )

        classDirectories.setFrom(files(javaDirectories, kotlinDirectories))
        additionalClassDirs.setFrom(files(coverageSrcDirectories))
        sourceDirectories.setFrom(files(coverageSrcDirectories))
        executionData.setFrom(
            files("${project.buildDir}/jacoco/${testTaskName}.exec"),
        )

        reports {
            xml.required.set(true)
            html.required.set(true)
        }

        doLast {
            jacocoTestReport(jacoco, "${testTaskName}Coverage")
        }
    }
}

private fun Project.jacocoTestReport(jacoco: JacocoPluginExtension, testTaskName: String) {
    val reportsDirectory = jacoco.reportsDirectory.asFile.get()
    val report = file("$reportsDirectory/${testTaskName}/${testTaskName}.xml")

    logger.lifecycle("Checking coverage results: $report")

    val limits = mutableMapOf<String, Double>()
    limits["instruction"] = 60.0
    limits["branch"] = 60.0

    val metrics = report.extractTestsCoveredByType()

    val failures = metrics.filter { entry ->
        entry.value <= (limits[entry.key] ?: 0.0)
    }.mapNotNull { entry ->
        limits[entry.key]?.let {
            "- ${entry.key} coverage rate is: ${entry.value}%, minimum is ${limits[entry.key]}%"
        }
    }

    if (failures.isNotEmpty()) {
        logger.quiet("------------------ Code Coverage Failed -----------------------")
        failures.forEach { logger.quiet(it) }
        logger.quiet("---------------------------------------------------------------")
        throw GradleException("Code coverage failed")
    }

    logger.quiet("------------------ Code Coverage Success -----------------------")
    metrics.forEach { entry ->
        logger.quiet("- ${entry.key} coverage rate is: ${entry.value}%")
    }
    logger.quiet("---------------------------------------------------------------")
}

private fun File.extractTestsCoveredByType(): Map<String, Double> {
    val xmlReader = XmlSlurper().apply {
        setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
        setFeature("http://apache.org/xml/features/disallow-doctype-decl", false)
    }

    val counterNodes: List<NodeChild> = xmlReader
        .parse(this).parent()
        .children()
        .filter {
            (it as NodeChild).name() == "counter"
        }.map { it as NodeChild }

    return counterNodes.associate { nodeChild ->
        val type = nodeChild.attributes()["type"].toString().lowercase(Locale.ENGLISH)

        val covered = nodeChild.attributes()["covered"].toString().toDouble()
        val missed = nodeChild.attributes()["missed"].toString().toDouble()
        val percentage = ((covered / (covered + missed)) * 10000.0).roundToInt() / 100.0

        Pair(type, percentage)
    }
}

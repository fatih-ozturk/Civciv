pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://jitpack.io")
    }
}
rootProject.name = "Civciv"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core:common")
include(":core:common-ui")
include(":core:data")
include(":core:database")
include(":core:designsystem")
include(":core:domain")
include(":core:mastodon-api")
include(":core:model")
include(":core:network")
include(":core:testing")
include(":feature:welcome")
include(":feature:splash")
include(":feature:profile")
include(":feature:home")
include(":feature:notification")
include(":feature:search")



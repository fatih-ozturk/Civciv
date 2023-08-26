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

include(":app")
include(":core:base")
include(":core:data")
include(":core:database")
include(":core:designsystem")
include(":core:domain")
include(":core:model")
include(":core:network")
include(":core:mastodon-api")
include(":core:testing")
include(":core:ui")
include(":feature:splash")
include(":feature:auth:graph")
include(":feature:auth:login")
include(":feature:auth:welcome")
include(":feature:auth:serverlist")
include(":feature:home:graph")
include(":feature:home:main")
include(":feature:profile:graph")
include(":feature:profile:main")
include(":feature:search:graph")
include(":feature:search:main")
include(":feature:notification:graph")
include(":feature:notification:main")



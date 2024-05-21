pluginManagement {
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
    }
}

rootProject.name = "LloydDemoApplication"
include(":app")
include(":network")
include(":userfeature")
include(":data")
include(":domain")
include(":common:core")
include(":common:ui")

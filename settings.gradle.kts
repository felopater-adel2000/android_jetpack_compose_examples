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

        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/felopater-adel2000/test_android_dependency")
            credentials {
                username = providers.gradleProperty("gpr.user").getOrElse("your_github_username")
                password =
                    providers.gradleProperty("gpr.token").getOrElse("your_personal_access_token")
            }
        }

    }
}

rootProject.name = "jetpack_compose_examples"
include(":app")
 
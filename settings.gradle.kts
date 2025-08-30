pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Android Basics"
include(":app")
include(":androidBasics:greetingCard")
include(":androidBasics:happyBirthday")
include(":androidBasics:article")
include(":androidBasics:businessCard")
include(":androidBasics:composeTutorial")
include(":androidBasics:diceRoller")

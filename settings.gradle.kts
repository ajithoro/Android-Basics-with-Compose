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
include(":androidBasics:lemonade")
include(":libraries:kotlin")
include(":libraries:android")
include(":androidBasics:finishScreen")
include(":androidBasics:tiptime")
include(":androidBasics:marsphotos")
include(":androidBasics:affirmations")
include(":androidBasics:courses")
include(":androidBasics:woof")
include(":androidBasics:superheroes")
include(":androidBasics:unscramble")
include(":androidBasics:dessertclicker")

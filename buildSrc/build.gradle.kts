import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import Build_gradle.Plugin.androidLib
import Build_gradle.Plugin.app
import Build_gradle.Plugin.kotlinLib
import Build_gradle.Plugin.ktlintPlugin

plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register(app) {
            id = app
            implementationClass = "plugin.ApplicationPlugin"
        }

        register(androidLib) {
            id = androidLib
            implementationClass = "plugin.AndroidLibraryPlugin"
        }

        register(kotlinLib) {
            id = kotlinLib
            implementationClass = "plugin.KotlinLibraryPlugin"
        }

        register(ktlintPlugin) {
            id = ktlintPlugin
            implementationClass = "plugin.KtlintPlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    languageVersion = Plugin.Version.kotlin
}

object Plugin {
    const val app: String = "app"
    const val androidLib: String = "androidLibrary"
    const val kotlinLib: String = "kotlinLibrary"
    const val ktlintPlugin: String = "ktlint"
    object Version {
        const val kotlin: String = "1.6.10"
        const val androidGradle: String = "7.1.1"
        const val navigation: String = "2.4.1"
        const val daggerHiltAndroid: String = "2.38.1"
        const val ktlint = "10.2.1"
        const val gmsServices = "4.3.10"
    }

    const val kotlin: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
    const val androidGradle: String = "com.android.tools.build:gradle:${Version.androidGradle}"
    const val navigationSafeArgs: String =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.navigation}"
    const val daggerHilt: String =
        "com.google.dagger:hilt-android-gradle-plugin:${Version.daggerHiltAndroid}"
    const val ktlint: String = "org.jlleitschuh.gradle:ktlint-gradle:${Version.ktlint}"
    const val gms = "com.google.gms:google-services:${Build_gradle.Plugin.Version.gmsServices}"
}

dependencies {
    implementation(Plugin.kotlin)
    implementation(Plugin.androidGradle)
    implementation(Plugin.navigationSafeArgs)
    implementation(Plugin.daggerHilt)
    implementation(Plugin.ktlint)
    implementation(Plugin.gms)
}

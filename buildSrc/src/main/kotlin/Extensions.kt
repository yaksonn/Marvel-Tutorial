import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.PluginContainer
import org.gradle.internal.impldep.junit.runner.Version.id
import org.gradle.kotlin.dsl.apply
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

internal val PluginContainer.kotlinAndroid: Unit
    get() {
        apply("org.jetbrains.kotlin.android")
    }

internal val PluginContainer.androidModule: Unit
    get() {
        apply("com.android.library")
    }

internal val PluginContainer.kotlinKapt: Unit
    get() {
        apply("kotlin-kapt")
    }

internal val PluginContainer.daggerHilt: Unit
    get() {
        apply("dagger.hilt.android.plugin")
    }

internal val PluginContainer.safeArgs: Unit
    get() {
        apply("androidx.navigation.safeargs.kotlin")
    }
internal val PluginContainer.gms: Unit
    get() {
        apply("com.google.gms.google-services")
    }

val PluginDependenciesSpec.daggerHilt: PluginDependencySpec
    get() = id("dagger.hilt.android.plugin")

val PluginDependenciesSpec.parcelize: PluginDependencySpec
    get() = id("kotlin-parcelize")

val PluginDependenciesSpec.androidApp: PluginDependencySpec
    get() = id("app")

val PluginDependenciesSpec.androidLib: PluginDependencySpec
    get() = id("androidLibrary")

val PluginDependenciesSpec.kotlinLib: PluginDependencySpec
    get() = id("kotlinLibrary")

val PluginDependenciesSpec.ktlint: PluginDependencySpec
    get() = id("ktlint")

val PluginDependenciesSpec.safeArgs: PluginDependencySpec
    get() = id("androidx.navigation.safeargs.kotlin")

val Project.applyKtlint
    get() = apply(plugin = "ktlint")

fun DependencyHandler.implementation(dependency: Any) = add(
    "implementation", dependency
)

fun DependencyHandler.implementAll(dependencies: List<String>) {
    dependencies.forEach(::implementation)
}

fun DependencyHandler.testImplementation(dependency: Any) = add(
    "testImplementation", dependency
)

fun DependencyHandler.androidTestImplementation(dependency: Any) = add(
    "androidTestImplementation", dependency
)

fun DependencyHandler.androidTestImplementation(vararg dependencies: Any) {
    dependencies.forEach(::androidTestImplementation)
}

fun DependencyHandler.testImplementation(vararg dependencies: Any) {
    dependencies.forEach(::testImplementation)
}

fun DependencyHandler.kapt(dependency: Any) = add(
    "kapt", dependency
)
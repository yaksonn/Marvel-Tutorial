import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    ktlint
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
subprojects project@{
    applyKtlint
    tasks.withType<KotlinCompile>().configureEach {
        with(kotlinOptions) {
            jvmTarget = JavaVersion.VERSION_11.toString()
            kotlinOptions.freeCompilerArgs +=
                "-Xopt-in=kotlin.Experimental," +
                        "kotlinx.coroutines.ExperimentalCoroutinesApi," +
                        "kotlinx.coroutines.InternalCoroutinesApi," +
                        "kotlinx.coroutines.FlowPreview"
        }
    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
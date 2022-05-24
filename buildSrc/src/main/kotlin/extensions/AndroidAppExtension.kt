package extensions

import BuildType.Companion.Debug
import BuildType.Companion.Release
import Config
import com.android.build.gradle.AppExtension
import org.gradle.api.JavaVersion

val ProjectExtension.Companion.AndroidApp: ProjectExtension
    get() = AndroidAppExtension()

private class AndroidAppExtension : ProjectExtension {
    override val name: String
        get() = "android"

    override fun configure(extension: Any) {
        if (extension !is AppExtension) return
        extension.apply {
            compileSdkVersion(Config.Version.compileSdkVersion)
            defaultConfig {
                applicationId = Config.Android.applicationId
                minSdk = Config.Version.minSdkVersion
                targetSdk = Config.Version.targetSdkVersion
                versionCode(Config.Version.versionCode)
                versionName(Config.Version.versionName)
                testInstrumentationRunner = Config.Android.testInstrumentationRunner
            }
            buildTypes {
                named(Debug.name) {
                    isMinifyEnabled = Debug.isMinifyEnabled
                    proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
                }
                named(Release.name) {
                    isMinifyEnabled = Release.isMinifyEnabled
                    proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }
        }
    }
}
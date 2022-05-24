package plugin

import Dependencies
import androidModule
import androidTestImplementation
import extensions.AndroidLib
import extensions.ProjectExtension
import implementAll
import implementation
import kotlinAndroid
import kotlinKapt
import testImplementation
import gms

class AndroidLibraryPlugin : BasePlugin() {
    override val pluginConfig: PluginConfig
        get() = {
            androidModule
            kotlinAndroid
            kotlinKapt
            gms
        }
    override val libraryConfig: LibraryConfig
        get() = {
            implementAll(Dependencies.AndroidX.components)
            implementation(Dependencies.Logging.timber)
            testImplementation(Dependencies.Test.junit)
            androidTestImplementation(
                Dependencies.Test.junitExt,
                Dependencies.Test.espresso
            )
            implementation("com.google.firebase:firebase-analytics-ktx:20.1.2")
        }
    override val extensions: Array<ProjectExtension>
        get() = arrayOf(ProjectExtension.AndroidLib)
}
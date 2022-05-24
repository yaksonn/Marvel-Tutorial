package plugin

import Dependencies
import androidTestImplementation
import extensions.AndroidApp
import extensions.ProjectExtension
import implementAll
import kotlinAndroid
import kotlinKapt
import testImplementation
import gms

class ApplicationPlugin : BasePlugin() {
    override val pluginConfig: PluginConfig
        get() = {
            apply(APP_PLUGIN_ID)
            kotlinAndroid
            kotlinKapt
            gms
        }
    override val libraryConfig: LibraryConfig
        get() = {
            implementAll(Dependencies.AndroidX.components)
            testImplementation(Dependencies.Test.junit)
            androidTestImplementation(Dependencies.Test.junitExt, Dependencies.Test.espresso)
        }
    override val extensions: Array<ProjectExtension>
        get() = arrayOf(ProjectExtension.AndroidApp)

    private companion object {
        const val APP_PLUGIN_ID: String = "com.android.application"
    }
}
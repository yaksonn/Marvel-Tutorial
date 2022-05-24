import ProjectLib.common
import ProjectLib.remote

plugins {
    androidLib
    daggerHilt
    parcelize
}

android.defaultConfig.javaCompileOptions {
    annotationProcessorOptions {
        arguments += Pair("room.incremental", "true")
    }
}
android.defaultConfig.buildConfigField("int", "databaseVersion", 1.toString())
android.defaultConfig.buildConfigField("String", "databaseName", "\"marvel_db\"")

android.defaultConfig.buildConfigField("String", "BASE_URL", "\"https://gateway.marvel.com/\"")

dependencies {

    //project lib
    implementation(project(remote))
    implementation(project(common))

    //network
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.moshi)

    //dagger hilt
    implementation(Dependencies.DI.daggerHiltAndroid)
    kapt(Dependencies.DI.AnnotationProcessor.daggerHilt)

    //paging 3
    implementation(Dependencies.Paging.paging)

    //swipe-refresh
    implementation(Dependencies.View.swipeRefreshLayout)

    //room
    implementation(Dependencies.DB.roomKtx)
    implementation(Dependencies.DB.roomRuntime)
    kapt(Dependencies.DB.roomCompiler)


    /* test */
    testImplementation(Dependencies.Test.mockWebServer)
    testImplementation(Dependencies.Test.truth)
    testImplementation(Dependencies.Network.retrofitMoshi)
    testImplementation(Dependencies.Network.moshi)
    testImplementation(Dependencies.Test.coroutines)
    testImplementation(Dependencies.Test.mockk)

    androidTestImplementation(Dependencies.Test.truth)
}
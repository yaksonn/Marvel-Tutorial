import ProjectLib.common
import ProjectLib.core
import ProjectLib.remote

plugins {
    androidApp
    parcelize
    daggerHilt
    safeArgs
}
kapt {
    correctErrorTypes = true
}

hilt {
    enableAggregatingTask = true
}

android.buildFeatures.viewBinding = true
dependencies {

    //project libs
    implementation(project(remote))
    implementation(project(core))
    implementation(project(common))

    //navigation
    implementation(Dependencies.Navigation.navigationUiKtx)
    implementation(Dependencies.Navigation.navigationFragmentKtx)
    implementation(Dependencies.Navigation.fragmentKtx)

    //Analytics
    implementation(Dependencies.FirebaseAnalytics.analytics)

    //hilt
    implementation(Dependencies.DI.daggerHiltAndroid)
    kapt(Dependencies.DI.AnnotationProcessor.daggerHilt)

    //paging 3
    implementation(Dependencies.Paging.paging)


    //swipe-refresh
    implementation(Dependencies.View.swipeRefreshLayout)

}
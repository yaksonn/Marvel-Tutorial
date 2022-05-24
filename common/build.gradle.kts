plugins {
    androidLib
}

dependencies {
    implementation(Dependencies.View.glide)
    implementation(Dependencies.Navigation.navigationUiKtx)
    implementation(Dependencies.Navigation.navigationFragmentKtx)
    implementation(Dependencies.Navigation.fragmentKtx)
    implementation(Dependencies.View.glide)
    kapt(Dependencies.View.glideAnnotation)
    implementation(Dependencies.View.swipeRefreshLayout)
}
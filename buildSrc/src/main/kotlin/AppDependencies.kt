import Dependencies.DB.Version.room_version
import Dependencies.FirebaseAnalytics.Version.analytics_version
import Dependencies.Paging.Version.paging_version
import Dependencies.View.Version.glideVersion

interface Libraries {
    val components: List<String>
}

object Dependencies {

    object AndroidX : Libraries {

        object Version {
            const val coreKtx_version = "1.7.0"
            const val appCompat_version = "1.4.1"
            const val material_version = "1.5.0"
            const val constraint_layout_version = "2.1.3"
            const val fragment_ktx_version = "1.4.1"
        }

        const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx_version}"
        const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat_version}"
        const val material = "com.google.android.material:material:${Version.material_version}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Version.constraint_layout_version}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Version.fragment_ktx_version}"

        override val components: List<String>
            get() = listOf(
                coreKtx, appCompat, material, constraintLayout
            )
    }

    object Navigation : Libraries {
        object Version {
            const val navigation: String = "2.4.2"
            const val fragment = "1.4.1"
        }

        const val navigationFragmentKtx: String =
            "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
        const val navigationUiKtx: String =
            "androidx.navigation:navigation-ui-ktx:${Version.navigation}"
        const val fragmentKtx: String = "androidx.fragment:fragment-ktx:${Version.fragment}"

        override val components: List<String>
            get() = listOf(
                navigationFragmentKtx, navigationUiKtx
            )
    }

    object View : Libraries {
        object Version {
            const val swipeRefreshLayout: String = "1.1.0"
            const val glideVersion: String = "4.12.0"
        }

        const val recyclerView = "androidx.recyclerview:recyclerview:1.2.1"

        const val glide: String = "com.github.bumptech.glide:glide:$glideVersion"

        const val glideAnnotation = "com.github.bumptech.glide:compiler:$glideVersion"

        const val swipeRefreshLayout: String =
            "androidx.swiperefreshlayout:swiperefreshlayout:${Version.swipeRefreshLayout}"
        override val components: List<String>
            get() = listOf(recyclerView)


    }

    object Network : Libraries {
        object Version {
            const val okhttp: String = "4.9.0"
            const val retrofit: String = "2.9.0"
            const val moshi: String = "1.13.0"
        }

        const val okhttp: String = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
        const val loggingInterceptor: String =
            "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
        const val retrofit: String = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val retrofitMoshi: String =
            "com.squareup.retrofit2:converter-moshi:${Version.retrofit}"
        const val moshi: String = "com.squareup.moshi:moshi-kotlin:${Version.moshi}"

        override val components: List<String> = listOf(
            okhttp, loggingInterceptor, retrofit, retrofitMoshi, moshi
        )
    }

    object DI {
        object Version {
            const val daggerHilt: String = "2.38.1"
        }

        object AnnotationProcessor {
            const val daggerHilt: String =
                "com.google.dagger:hilt-compiler:${Version.daggerHilt}"
        }

        const val daggerHiltAndroid: String =
            "com.google.dagger:hilt-android:${Version.daggerHilt}"
        const val hiltCore: String = "com.google.dagger:hilt-core:${Version.daggerHilt}"
    }

    object DB : Libraries {
        object Version {
            const val room_version = "2.4.0-alpha03"
        }

        const val roomRuntime = "androidx.room:room-runtime:$room_version"
        const val roomKtx = "androidx.room:room-ktx:$room_version"
        const val roomCompiler = "androidx.room:room-compiler:$room_version"

        override val components: List<String>
            get() = listOf(
                roomKtx, roomRuntime
            )
    }

    object Logging {
        object Version {
            const val timber = "4.7.1"
        }

        const val timber = "com.jakewharton.timber:timber:${Version.timber}"
    }

    object Paging {
        object Version {
            const val paging_version = "3.1.1"
        }

        const val paging = "androidx.paging:paging-runtime:$paging_version"
    }

    object FirebaseAnalytics {
        object Version {
            const val analytics_version = "21.0.0"
        }

        const val analytics = "com.google.firebase:firebase-analytics-ktx:$analytics_version"
    }

    object Test {
        object Version {
            const val junit_version = "4.13.2"
            const val junit_ext_version = "1.1.3"
            const val espresso_version = "3.4.0"
            const val mockWebServer_version = "4.9.3"
            const val mockK_version = "1.12.2"
            const val truth = "1.1.3"
            const val coroutines: String = "1.5.2"
            const val roboElectric: String = "4.6"
        }

        const val junit = "junit:junit:${Version.junit_version}"
        const val junitExt = "androidx.test.ext:junit:${Version.junit_ext_version}"
        const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso_version}"
        const val mockWebServer =
            "com.squareup.okhttp3:mockwebserver:${Version.mockWebServer_version}"
        const val mockk = "io.mockk:mockk:${Version.mockK_version}"
        const val truth = "com.google.truth:truth:${Version.truth}"
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutines}"
        const val roboElectric = "org.robolectric:robolectric:${Version.roboElectric}"
    }
}
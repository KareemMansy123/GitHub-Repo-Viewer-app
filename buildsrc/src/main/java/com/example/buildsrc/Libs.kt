package com.example.buildsrc

object Libs {
    // add your dependencies here for koin
    // koin for DI implementation("io.insert-koin:koin-android:3.4.3") and implementation("io.insert-koin:koin-androidx-compose:3.4.3")
    object Koin {
        const val koin = "io.insert-koin:koin-android:3.4.3"
        const val koinAndroidxCompose = "io.insert-koin:koin-androidx-compose:3.4.3"
    }

    object ComposeLibs {
        const val version = "1.0.5"
        const val activity = "androidx.activity:activity-compose:1.4.0"
        const val ui = "androidx.compose.ui:ui:$version"
        const val material = "androidx.compose.material:material:$version"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"

        private const val navVersion = "2.4.2"
        const val navigation = "androidx.navigation:navigation-compose:$navVersion"

        //androidTestImplementation "androidx.compose.ui:ui-test-junit4:$compose_version"
        //debugImplementation "androidx.compose.ui:ui-tooling:$compose_version"
    }

    object CoroutinesLibs {
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0"
    }

    object Ktor {
        const val ktorClientCore = "io.ktor:ktor-client-core:1.6.3"
        const val ktorClientAndroid = "io.ktor:ktor-client-android:1.6.3"
        const val ktorClientJson = "io.ktor:ktor-client-json:1.6.3"
        const val ktorClientLogging = "io.ktor:ktor-client-logging:1.6.3"
        const val ktorClientSerialization = "io.ktor:ktor-client-serialization:1.6.3"
        const val ktorClientSerializationJvm = "io.ktor:ktor-client-serialization-jvm:1.6.3"
        const val ktorClientSerializationNative = "io.ktor:ktor-client-serialization-native:1.6.3"
        const val ktorClientMock = "io.ktor:ktor-client-mock:1.6.3"
        const val ktorClientOkhttp = "io.ktor:ktor-client-okhttp:1.6.3"
        }
}




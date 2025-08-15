plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("org.jetbrains.kotlin.plugin.compose")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.restart.jetpack_compose_examples"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.restart.jetpack_compose_examples"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    packaging {
        resources {
            excludes += setOf(
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md"
            )
        }
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.16.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.2")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    testImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.6.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    //Navigation Component
    val nav_version = "2.9.3"
    implementation("androidx.navigation:navigation-fragment:${nav_version}")
    implementation("androidx.navigation:navigation-ui:${nav_version}")
    implementation("androidx.navigation:navigation-runtime-ktx:${nav_version}")

    //Koin
    implementation("io.insert-koin:koin-core:4.1.0")
    implementation("io.insert-koin:koin-android:4.1.0")
    implementation("io.insert-koin:koin-androidx-compose:4.1.0")
    implementation("io.insert-koin:koin-androidx-navigation:4.1.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")

    // OKHttpLoggingInterceptor
    implementation("com.squareup.okhttp3:logging-interceptor:5.1.0")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.1.7")

    // Kotlin Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")


    /** Testing **/

    // Core & JUnit
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")
    testImplementation("androidx.test:core:1.7.0")
    androidTestImplementation("androidx.test:core:1.7.0")
    androidTestImplementation("androidx.arch.core:core-testing:2.2.0")


    // Compose UI Test
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    testImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Mockk
    val mockkVersion = "1.14.5"
    testImplementation("io.mockk:mockk-android:${mockkVersion}")
    testImplementation("io.mockk:mockk-agent:${mockkVersion}")
    androidTestImplementation("io.mockk:mockk-android:${mockkVersion}")
    androidTestImplementation("io.mockk:mockk-agent:${mockkVersion}")

    // Test Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:2.9.3")

    // Test Fragment
    val fragment_version = "1.8.8"
    debugImplementation("androidx.fragment:fragment-testing-manifest:$fragment_version")
    androidTestImplementation("androidx.fragment:fragment-testing:$fragment_version")

    // Test Koin
    androidTestImplementation("io.insert-koin:koin-test:4.1.0")
    testImplementation("io.insert-koin:koin-test:4.1.0")
    testImplementation("io.insert-koin:koin-test-junit4:4.1.0")
    androidTestImplementation("io.insert-koin:koin-test-junit4:4.1.0")

    // Test OkHttp
    testImplementation("com.squareup.okhttp3:mockwebserver:5.1.0")
    androidTestImplementation("com.squareup.okhttp3:mockwebserver:5.1.0")
    androidTestImplementation("com.squareup.okhttp3:okhttp-tls:5.1.0")
    androidTestImplementation("com.jakewharton.espresso:okhttp3-idling-resource:1.0.0")
    androidTestImplementation("androidx.test.espresso:espresso-idling-resource:3.7.0")

    // Robolectric
    testImplementation("org.robolectric:robolectric:4.15.1")
}
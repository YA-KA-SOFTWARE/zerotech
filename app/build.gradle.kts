plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.yakasoftware.zerotech"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.yakasoftware.zerotech"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    //Compose
    implementation ("androidx.compose.animation:animation:1.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.navigation:navigation-compose:2.7.2")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha12")
    implementation("androidx.compose.material:material-icons-core:1.5.1")
    implementation("androidx.compose.material:material-icons-extended:1.5.1")
    implementation("io.coil-kt:coil-compose:2.4.0")
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx")
    //Firebase
    implementation (platform("com.google.firebase:firebase-bom:32.2.0"))
    implementation ("com.google.firebase:firebase-analytics-ktx:21.3.0")
    implementation ("com.firebaseui:firebase-ui-auth:8.0.2")
    implementation ("com.google.android.gms:play-services-auth:20.7.0")
    implementation ("com.google.firebase:firebase-firestore-ktx:24.7.1")
    implementation ("com.google.firebase:firebase-storage-ktx:20.2.1")
    implementation ("com.google.firebase:firebase-appcheck:17.0.1")
    implementation ("com.google.firebase:firebase-crashlytics-ktx:18.4.1")
    implementation ("com.google.firebase:firebase-messaging:23.2.1")
    //Hilt
    implementation ("com.google.dagger:hilt-android:2.47")
    implementation ("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")

    implementation ("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha12")
    implementation ("androidx.compose.material:material-icons-extended:1.5.1")
    implementation("androidx.compose.ui:ui-tooling:1.5.1")
    implementation ("androidx.compose.foundation:foundation:1.5.1")
    implementation ("androidx.compose.runtime:runtime-livedata:1.5.1")
    implementation ("androidx.navigation:navigation-compose:2.7.2")
    implementation ("androidx.compose.foundation:foundation-layout:1.6.0-alpha05")

    //Location
    implementation ("com.google.android.gms:play-services-location:21.0.1")
    implementation ("com.google.accompanist:accompanist-pager:0.33.0-alpha")

    //ALgolia
    implementation ("com.algolia:instantsearch-compose:3.3.0")
    implementation ("io.ktor:ktor-client-okhttp:2.3.3")

    //For Premium
    implementation ("com.android.billingclient:billing:6.0.1")
    //Accompanist for WindowInsets
    implementation ("com.google.accompanist:accompanist-insets:0.31.5-beta")
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.16.0")

}
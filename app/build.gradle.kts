plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.cafeduoc"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.cafeduoc"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    buildToolsVersion = "35.0.0"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation("io.reactivex.rxjava3:rxjava:3.1.5")
    implementation(libs.androidx.room.common.jvm)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime.compose.android)
    implementation(libs.androidx.navigation.compose.android)
    implementation(libs.androidx.ui.test.junit4.android)
    testImplementation(libs.junit)
    // 2. MockK: La librería para crear mocks (simulaciones) de tus dependencias en Kotlin.
    // Reemplaza '1.13.10' con la última versión disponible.
    testImplementation("io.mockk:mockk:1.13.10")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("androidx.test.ext:junit:1.1.5")
    testImplementation("org.robolectric:robolectric:4.12.1")
    testImplementation(libs.androidx.ui.test.android)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.14")
    // En build.gradle.kts (:app)
    // Reemplaza con las versiones más recientes
    // Dependencias de Glide Core
    implementation("com.github.bumptech.glide:glide:4.16.0")
    // Dependencia para la integración con Jetpack Compose
    implementation("com.github.bumptech.glide:compose:1.0.0-beta01")
    // Dependencia principal de Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Dependencia del conversor Gson para manejar JSON
    // Si prefieres usar Moshi, sería "com.squareup.retrofit2:converter-moshi"
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.dagger:hilt-android:2.56.2")
    implementation("com.google.dagger:hilt-android-compiler:2.56.2")


}
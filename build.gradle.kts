// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    // build.gradle.kts (a nivel de proyecto)
    // Reemplaza con la última versión
    id("com.google.dagger.hilt.android") version "2.56.2" apply false
}
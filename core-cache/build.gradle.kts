import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { it ->
        it.binaries.framework {
            baseName = "datasource"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            // Koin
            implementation(libs.androidx.datastore.preferences)
            implementation(libs.koin.core)
            implementation(libs.kotlinx.serialization.json)
        }

        androidMain.dependencies {
            // Android-specific dependencies if any
            implementation(libs.koin.android)
        }
    }
}

android {
    namespace = "voxy.friend.chat.core.cache"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
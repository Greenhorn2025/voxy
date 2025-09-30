import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    // Add the same iOS targets as your composeApp
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { _ ->
    }

    sourceSets {
        commonMain.dependencies {
            // Ktor
            implementation(libs.kotlinx.serialization.json)

            implementation(compose.material3)
            api(libs.sdp.ssp.compose.multiplatform)
            implementation(compose.components.resources)
            implementation(compose.runtime)
            implementation(compose.foundation)

            api(libs.cmptoast)
            api(libs.navigation.compose)

            // Google Phone Hint
            implementation(libs.coil.compose)
        }

        androidMain.dependencies {
            // Android-specific dependencies if any
        }

        iosMain.dependencies {
            // iOS-specific dependencies if any
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "voxy.friend.chat.common"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}


dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.testExt.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
    alias(libs.plugins.kotlinx.serialization)
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

    sourceSets.commonMain {
        kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
    }

    sourceSets {
        commonMain.dependencies {
            // Ktor
            api(projects.coreCache)
            
            implementation(libs.kotlinx.serialization.json)

            implementation(compose.material3)
            api(libs.sdp.ssp.compose.multiplatform)
            implementation(compose.components.resources)
            implementation(compose.runtime)
            implementation(compose.foundation)

            api(libs.cmptoast)
            api(libs.navigation.compose)

            implementation(libs.koin.core)
            // Google Phone Hint
            implementation(libs.coil.compose)

            implementation(libs.ktor.serialization.kotlinx.json)

            implementation(libs.androidx.room.runtime)
            implementation(libs.sqlite.bundled)
        }

        androidMain.dependencies {
            // Android-specific dependencies if any
            api(libs.truecaller.sdk)
            implementation(libs.androidx.work.runtime)
            implementation(libs.koin.android)
        }

        iosMain.dependencies {
            // iOS-specific dependencies if any
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
room {
    schemaDirectory("$projectDir/schemas")
    generateKotlin = true
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
    //KSP for room
//    add("kspAndroid", "androidx.room:room-compiler:2.6.1")
    add("kspCommonMainMetadata", libs.room.compiler)
    add("kspAndroid", libs.room.compiler)
    add("kspIosX64", libs.room.compiler)
    add("kspIosArm64", libs.room.compiler)
    add("kspIosSimulatorArm64", libs.room.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.testExt.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
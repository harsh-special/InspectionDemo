import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.hiltAndroid)
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.domain"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
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
}

dependencies {

    implementation(project(Modules.commonCore))

    // Dagger with hilt
    implementation(Libraries.hiltAndroid)
    kapt(Libraries.hiltAndroidCompiler)

    // test libraries
    testImplementation(Libraries.junit)
    testImplementation(Libraries.coroutineTest)
    testImplementation(Libraries.mockk)
    testImplementation(Libraries.gson)
}

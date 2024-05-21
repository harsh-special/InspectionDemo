plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.hiltAndroid)
}

android {
    namespace = "com.example.userfeature"
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
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
}

dependencies {
    implementation(Libraries.coreKtx)

    // Compose
    implementation(Libraries.composeUi)
    debugImplementation(Libraries.composeUiRuntime)
    implementation(Libraries.materialCompose)
    implementation(Libraries.hiltNavigationCompose)

    // lifecycle compose
    implementation(Libraries.lifeCycleRuntimeCompose)

    // Hilt
    implementation(Libraries.hiltAndroid)
    kapt(Libraries.hiltAndroidCompiler)

    implementation(project(Modules.commonUI))
    implementation(project(Modules.commonCore))
    implementation(project(Modules.domain))

    // test libraries
    testImplementation(Libraries.junit)
    testImplementation(Libraries.coroutineTest)
    testImplementation(Libraries.mockk)
    testImplementation(Libraries.gson)
}
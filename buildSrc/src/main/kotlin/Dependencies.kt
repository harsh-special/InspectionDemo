object BuildPlugins {
    val androidApplication by lazy { "com.android.application" }
    val androidLibrary by lazy { "com.android.library" }
    val kotlinAndroid by lazy { "org.jetbrains.kotlin.android" }
    val kotlinKapt by lazy { "org.jetbrains.kotlin.kapt" }
    val hiltAndroid by lazy { "com.google.dagger.hilt.android" }
}

object Libraries {
    val gson by lazy { "com.google.code.gson:gson:${Versions.gson}" }
    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
    val lifecycle by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}" }
    val lifeCycleRuntimeCompose by lazy { "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycle}" }
    val material by lazy { "com.google.android.material:material:${Versions.material}" }
    val appcompat by lazy { "androidx.appcompat:appcompat:${Versions.appcompat}" }
    val navigationUiKtx by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navigation}" }
    val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
    val hiltAndroidCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hilt}" }
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val retrofitGsonConverter by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofit}" }
    val okhttp by lazy { "com.squareup.okhttp3:okhttp:${Versions.okhttp}" }
    val okhttpLoggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}" }
    val composeUi by lazy { "androidx.compose.ui:ui:${Versions.compose}" }
    val composeUiRuntime by lazy { "androidx.compose.runtime:runtime:${Versions.compose}" }
    val materialCompose by lazy { "androidx.compose.material:material:${Versions.compose}" }
    val hiltNavigationCompose by lazy { "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}" }
    val junit by lazy { "junit:junit:${Versions.junit}" }
    val mockk by lazy { "io.mockk:mockk:${Versions.mockk}" }
    val coroutineTest by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutineTest}" }
}

object Modules {
    const val commonCore = ":common:core"
    const val commonUI = ":common:ui"
    const val data = ":data"
    const val domain = ":domain"
    const val network = ":network"
    const val userFeature = ":userfeature"
}
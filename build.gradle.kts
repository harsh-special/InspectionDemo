// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(BuildPlugins.androidApplication) version Versions.androidApplication apply false
    id(BuildPlugins.kotlinAndroid) version Versions.coreKtx apply false
    id(BuildPlugins.hiltAndroid) version Versions.hilt apply false
}
buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        dependencies {
            classpath(libs.gradle)
            classpath(libs.kotlin.gradle.plugin)
            classpath(libs.androidx.navigation.safe.args.gradle.plugin)
        }
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}
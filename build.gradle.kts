plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
}

buildscript{
    val navigation_version = "2.5.0"
    val hilt_version = "2.44"

    repositories{
        google()
        mavenCentral()
    }

    dependencies {
        classpath ("com.android.tools.build:gradle:7.0.4")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:$navigation_version")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:$hilt_version")
    }
}
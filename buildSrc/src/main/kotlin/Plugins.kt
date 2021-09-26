object Plugins {
    const val buildGradle = "com.android.tools.build:gradle:${Versions.BUILD_GRADLE}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    const val serialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.KOTLIN}"
    const val versionsClassPath =
        "com.github.ben-manes:gradle-versions-plugin:${Versions.VERSIONS_CHECK}"

    const val application = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val androidLibrary = "com.android.library"
    const val javaLibrary = "java-library"
    const val kotlinMin = "kotlin"
    const val kapt = "kotlin-kapt"
    const val parcelize = "kotlin-parcelize"
    const val versions = "com.github.ben-manes.versions"
}
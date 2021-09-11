buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(dependencyNotation = "com.android.tools.build:gradle:7.0.0")
        classpath(dependencyNotation = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.20")
    }
}

task("clean") {
    delete(rootProject.buildDir)
}
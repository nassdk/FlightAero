buildscript {

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(dependencyNotation = Plugins.buildGradle)
        classpath(dependencyNotation = Plugins.kotlin)
        classpath(dependencyNotation = Plugins.serialization)
        classpath(dependencyNotation = Plugins.versionsClassPath)
    }
}

task(Tasks.Clean) {
    delete(rootProject.buildDir)
}
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
    }
}

task(Tasks.Clean) {
    delete(rootProject.buildDir)
}
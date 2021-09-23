buildscript {

    repositories {
        google()
        mavenCentral()
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
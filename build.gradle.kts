buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(dependencyNotation = Plugins.buildGradle)
        classpath(dependencyNotation = Plugins.kotlin)
        classpath(dependencyNotation = Plugins.serialization)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.20")
    }
}

task(Tasks.Clean) {
    delete(rootProject.buildDir)
}
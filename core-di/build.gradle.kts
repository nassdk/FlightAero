plugins {
    id(Plugins.javaLibrary)
    id(Plugins.kotlinMin)
    id(Plugins.kapt)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    api(dependencyNotation = Dependencies.dagger)
    kapt(dependencyNotation = Dependencies.daggerCompiler)
}
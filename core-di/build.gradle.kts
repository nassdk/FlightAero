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
    api(Dependencies.dagger)
    kapt(Dependencies.daggerCompiler)
}
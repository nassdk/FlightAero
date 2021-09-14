import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.implementation(dependencyNotation: String) {
    add("implementation", dependencyNotation)
}

internal fun DependencyHandler.androidTestImplementation(dependencyNotation: String) {
    add("androidTestImplementation", dependencyNotation)
}

internal fun DependencyHandler.kapt(dependencyNotation: String) {
    add("kapt", dependencyNotation)
}

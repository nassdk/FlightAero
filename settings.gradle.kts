enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {

    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "FlightAero"

include(":app")
include(":core-network")
include(":core-navigation")
include(":core-di")
include(":core-ui")
include(":core-common")

include(":feature-splash")
include(":feature-flow")
include(":feature-flights")
include(":feature-profile")

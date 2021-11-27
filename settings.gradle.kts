dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
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
//project(":app").projectDir = File(rootDir, "app")

//setOf("$rootDir/core").forEach { dirName ->
//    File(dirName).listFiles()?.filter { it.isDirectory }?.forEach { module ->
//        val moduleName = module.name
//        val gradleFile = File("${module.absolutePath}/build.gradle")
//        val gradleFileKts = File("${module.absolutePath}/build.gradle.kts")
//        // Если в директории есть файл build.gradle, то это модуль
//        if (gradleFile.exists() || gradleFileKts.exists()) {
//            // Подключаем модуль к проекту
//            include(":$moduleName")
//            project(":$moduleName").projectDir = File("$dirName/$moduleName")
//            logger.info("include module: $moduleName")
//        }
//    }
//}

include(":feature-splash")
include(":feature-flow")
include(":feature-flights")
include(":feature-profile")

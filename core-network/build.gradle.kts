plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
}

android {
    compileSdk = AndroidConfig.COMPILE_SDK
    buildToolsVersion = AndroidConfig.BUILD_TOOLS_VERSION

    defaultConfig {
        minSdk = AndroidConfig.MIN_SDK
        targetSdk = AndroidConfig.TARGET_SDK

        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTAL_RUNNER
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(name = "proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        buildConfig = true
    }
}


dependencies {
    implementation(project(path = ":core-di"))

    api(dependencyNotation = Dependencies.retrofit)
    api(dependencyNotation = Dependencies.serializationJson)

    api(dependencyNotation = Dependencies.serializationConverter)
    api(dependencyNotation = Dependencies.okHttp3)
    api(dependencyNotation = Dependencies.logging)
    kapt(dependencyNotation = Dependencies.daggerCompiler)
}
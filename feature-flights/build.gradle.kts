plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.parcelize)
    id(Plugins.kapt)
    id(Plugins.kotlinxSerialization)
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
        viewBinding = true
    }
}

dependencies {
    implementation(dependencyNotation = projects.coreCommon)

    testImplementation(dependencyNotation = Dependencies.coroutinesTest)
    testImplementation(dependencyNotation = Dependencies.jUnit)
    testImplementation(dependencyNotation = Dependencies.mockitoKotlin)

    implementation(dependencyNotation = Dependencies.shimmer)

    kapt(dependencyNotation = Dependencies.daggerCompiler)
}
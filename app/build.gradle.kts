plugins {
    id(Plugins.application)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
}

android {
    compileSdk = AndroidConfig.COMPILE_SDK
    buildToolsVersion = AndroidConfig.BUILD_TOOLS_VERSION

    defaultConfig {
        applicationId = AndroidConfig.APPLICATION_ID
        minSdk = AndroidConfig.MIN_SDK
        targetSdk = AndroidConfig.TARGET_SDK
        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME

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
        buildConfig = true
    }
}

dependencies {

    projects.run {
        implementation(dependencyNotation = coreCommon)
        implementation(dependencyNotation = featureSplash)
        implementation(dependencyNotation = featureFlow)
        implementation(dependencyNotation = featureFlights)
        implementation(dependencyNotation = featureProfile)
    }

    androidTestImplementation(dependencyNotation = Dependencies.kaspresso)
    androidTestImplementation(dependencyNotation = Dependencies.jUnit)

    implementation(dependencyNotation = Dependencies.coreKtx)
    implementation(dependencyNotation = Dependencies.appCompat)
}
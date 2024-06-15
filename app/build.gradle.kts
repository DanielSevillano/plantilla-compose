plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin)
}

android {
    namespace = "com.daniel.compose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.daniel.compose"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildToolsVersion = "34.0.0"
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.activity.compose)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui)
    implementation(libs.core.splashscreen)
    implementation(libs.navigation.compose)
    //implementation(libs.core.ktx)
    //implementation(libs.lifecycle.runtime.ktx)
    //implementation(libs.ui.graphics)
    //implementation(libs.ui.tooling.preview)
    //testImplementation(libs.junit)
    //androidTestImplementation(libs.androidx.test.ext.junit)
    //androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    //androidTestImplementation(libs.ui.test.junit4)
    //debugImplementation(libs.ui.tooling)
    //debugImplementation(libs.ui.test.manifest)
}
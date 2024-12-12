plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.miguelu00.peliculeo_android"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.miguelu00.peliculeo_android"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit for HTTP requests
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Gson Converter for Retrofit
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0") // Logging interceptor for HTTP requests
    implementation("com.github.bumptech.glide:glide:4.12.0") // Glide for image loading
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0") // Glide's annotation processor

    // Lombok
    implementation("org.projectlombok:lombok:1.18.30")

// Para que funcione durante la compilación:
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
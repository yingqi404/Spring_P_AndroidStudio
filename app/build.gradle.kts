plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "jp.ac.jec.cm0105.android_springboot_wyq"
    compileSdk = 35

    defaultConfig {
        applicationId = "jp.ac.jec.cm0105.android_springboot_wyq"
        minSdk = 30
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.gridlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("com.google.android.material:material:1.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.4.1")//导入第三方okhttp依赖用来发送网络请求
}
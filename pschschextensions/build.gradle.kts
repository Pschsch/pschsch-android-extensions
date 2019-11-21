plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-android-extensions")
    id("com.github.dcendents.android-maven")
}

group = "com.github.Pschsch"

android {
    compileSdkVersion(28)

    defaultConfig {
        minSdkVersion(14)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}
val kotlinVersion = "1.3.50"
val lifecycleVersion = "2.1.0"
val coreAppCompatVersion = "1.1.0"
val coroutinesVersion = "1.3.0"
val socketIOVersion = "1.0.0"
val googleLocation = "17.0.0"

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion")
    implementation("androidx.appcompat:appcompat:$coreAppCompatVersion")
    implementation("androidx.core:core-ktx:$coreAppCompatVersion")
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    implementation("com.google.android.gms:play-services-location:$googleLocation")
    implementation("io.socket:socket.io-client:$socketIOVersion") {
        exclude(group = "org.json", module = "json")
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:3.5.2")
        classpath("com.github.dcendents:android-maven-gradle-plugin:2.1")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.50")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks {
    registering(Delete::class) {
        delete(rootProject.buildDir)
    }
}

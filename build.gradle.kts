// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(AppGradleClassPathes.gradleClassPath)
        classpath(AppGradleClassPathes.kotlinClassPath)
        classpath(AppGradleClassPathes.safeArgsClassPath)
        classpath(AppGradleClassPathes.daggerHiltClassPath)
        classpath(AppGradleClassPathes.dependenciesPlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = uri("https://jitpack.io"))
        maven(url = ("https://plugins.gradle.org/m2/"))
    }
}

tasks.register("clear",Delete::class){
    delete(rootProject.buildDir)
}
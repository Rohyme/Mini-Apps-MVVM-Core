/**
 * Created by rohyme on 11/20/20.
 **/
object AppGradleClassPathes {
    const val kotlinClassPath = "org.jetbrains.kotlin:kotlin-gradle-plugin:${BuildPluginsVersion.kotlinVersion}"
    const val gradleClassPath = "com.android.tools.build:gradle:${BuildPluginsVersion.gradleVersion}"
    const val safeArgsClassPath = "androidx.navigation:navigation-safe-args-gradle-plugin:${BuildPluginsVersion.safeArgsVersion}"
    const val daggerHiltClassPath = "com.google.dagger:hilt-android-gradle-plugin:${BuildPluginsVersion.daggerHiltVersion}"
    const val dependenciesPlugin = "name.remal:gradle-plugins:${BuildPluginsVersion.dependenciesUpdateVersion}"
}

object BuildPluginsVersion {
    const val dependenciesUpdateVersion = "1.1.4"
    const val daggerHiltVersion = "2.28-alpha"
    const val gradleVersion = "4.1.1"
    const val kotlinVersion = "1.3.61"
    const val safeArgsVersion = "2.2.0"
}

object TopLevelPlugins {
    const val androidApplication = "com.android.application"
    const val kotlinKapt = "kapt"
    const val kotlinAndroid = "android"
    const val daggerHilt = "dagger.hilt.android.plugin"
    const val updateDependencies = "name.remal.check-dependency-updates"
}
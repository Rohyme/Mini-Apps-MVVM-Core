plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
}



android {
    compileSdkVersion(MainAppVersions.compileSdk)
    defaultConfig {
        applicationId = APPLICATION_ID
        minSdkVersion(MainAppVersions.minSdk)
        targetSdkVersion(MainAppVersions.targetSdk)
        versionCode = MainAppVersions.versionCode
        versionName = MainAppVersions.versionName
        multiDexEnabled = true
        testInstrumentationRunner = TestLibs.testInstrumentationRunner
    }
    dataBinding {
        isEnabled = true
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-dev"
        }

        getByName("release") {
            isMinifyEnabled = true
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
            isZipAlignEnabled = true
            isJniDebuggable = false
            isRenderscriptDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
}
dependencies {
    implementation(AndroidSdkLibs.activity)
    implementation(AndroidSdkLibs.fragment)
    implementation(AndroidSdkLibs.appCompat)
    implementation(AndroidSdkLibs.appCompatOld)
    implementation(AndroidSdkLibs.constraintLayout)
    implementation(AndroidSdkLibs.core)
    implementation(AndroidSdkLibs.lifeCycle)
    implementation(AndroidSdkLibs.paging)
    implementation(AndroidSdkLibs.room)
    implementation(AndroidSdkLibs.roomKtx)
    implementation(AndroidSdkLibs.roomRx)
    kapt(AndroidSdkLibs.roomAnnotationProcess)
    implementation(AndroidSdkLibs.material)
    implementation(AndroidSdkLibs.navigationFragments)
    implementation(AndroidSdkLibs.navigationUi)
    implementation(AndroidSdkLibs.workManager)
    implementation(AndroidSdkLibs.workManagerRx)
    implementation(AndroidSdkLibs.recyclerView)
    implementation(AndroidSdkLibs.saveState)

    implementation(ThirdPartyLibs.rxJava)
    implementation(ThirdPartyLibs.rxAndroid)
    implementation(ThirdPartyLibs.subjectRelayRx)
    implementation(ThirdPartyLibs.retrofit)
    implementation(ThirdPartyLibs.retrofitRxAdapter)
    implementation(ThirdPartyLibs.okHttp)
    implementation(ThirdPartyLibs.gson)
    implementation(ThirdPartyLibs.gsonConverter)
    implementation(ThirdPartyLibs.interceptor)
    implementation(ThirdPartyLibs.dagger2)
    kapt(ThirdPartyLibs.daggerAnnotationProcess)
    kapt(ThirdPartyLibs.daggerCompiler)
    implementation(ThirdPartyLibs.daggerSupport)
    implementation(ThirdPartyLibs.textSizing)
    implementation(ThirdPartyLibs.viewSizing)
    implementation(ThirdPartyLibs.fonts)
    implementation(ThirdPartyLibs.glide)
    kapt(ThirdPartyLibs.glideAnnotationProcess)
    kapt(ThirdPartyLibs.circleImageView)
    kapt(ThirdPartyLibs.prettyStateView)
}



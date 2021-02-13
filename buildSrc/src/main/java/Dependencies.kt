

object ThirdPartyLibs {
    const val daggerHilt = "com.google.dagger:hilt-android:${ThirdPartyLibsVersions.daggerHiltVersion}"
    const val daggerHiltCompiler = "com.google.dagger:hilt-android-compiler:${ThirdPartyLibsVersions.daggerHiltVersion}"
    const val daggerHiltWithLifeCycle = "androidx.hilt:hilt-lifecycle-viewmodel:${ThirdPartyLibsVersions.daggerLifeCycleVersion}"
    const val daggerHiltProcessor = "androidx.hilt:hilt-compiler:${ThirdPartyLibsVersions.daggerLifeCycleVersion}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${ThirdPartyLibsVersions.retrofitVersion}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${ThirdPartyLibsVersions.okHttpVersion}"
    const val interceptor = "com.squareup.okhttp3:logging-interceptor:${ThirdPartyLibsVersions.interceptorVersion}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${ThirdPartyLibsVersions.moshiConverterVersion}"
    const val viewSizing = "com.intuit.sdp:sdp-android:${ThirdPartyLibsVersions.sizingVersions}"
    const val textSizing = "com.intuit.ssp:ssp-android:${ThirdPartyLibsVersions.sizingVersions}"
    const val fonts = "uk.co.chrisjenx:calligraphy:${ThirdPartyLibsVersions.calligraphyVersions}"
    const val glide = "com.github.bumptech.glide:glide:${ThirdPartyLibsVersions.glideVersion}"
    const val glideAnnotationProcess = "com.github.bumptech.glide:compiler:${ThirdPartyLibsVersions.glideVersion}"
    const val circleImageView = "de.hdodenhof:circleimageview:${ThirdPartyLibsVersions.circleImageView}"
    const val prettyStateView = "com.github.Tripl3Dev:PrettyStateView:${ThirdPartyLibsVersions.prettyStateView}"
}
object KotlinLibs {
    const val coroutines ="org.jetbrains.kotlinx:kotlinx-coroutines-core:${KotlinVersions.coroutinesVersion}"
}
object AndroidSdkLibs {
    const val activity = "androidx.activity:activity-ktx:${AndroidSdkVersions.activityVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${AndroidSdkVersions.appCompatVersion}"
    // For loading and tinting drawables on older versions of the platform
    const val appCompatOld = "androidx.appcompat:appcompat-resources:${AndroidSdkVersions.appCompatVersion}"
    const val fragment = "androidx.fragment:fragment-ktx:${AndroidSdkVersions.fragmentsVersion}"
    const val core = "androidx.core:core-ktx:${AndroidSdkVersions.coreVersion}"
    const val lifeCycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:${AndroidSdkVersions.lifeCycleVersion}"
    const val navigationFragments = "androidx.navigation:navigation-fragment-ktx:${AndroidSdkVersions.navigationVersion}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${AndroidSdkVersions.navigationVersion}"
    const val room = "androidx.room:room-runtime:${AndroidSdkVersions.roomVersion}"
    const val roomKtx = "androidx.room:room-ktx:${AndroidSdkVersions.roomVersion}"
    const val roomAnnotationProcess= "androidx.room:room-compiler:${AndroidSdkVersions.roomVersion}"
    const val saveState = "androidx.savedstate:savedstate:${AndroidSdkVersions.saveStateVersion}"
    const val paging = "androidx.paging:paging-runtime:${AndroidSdkVersions.pagingVersion}"
    const val material = "com.google.android.material:material:${AndroidSdkVersions.materialVersion}"
    const val workManager = "androidx.work:work-runtime-ktx:${AndroidSdkVersions.workManagerVersion}"
    const val workManagerRx = "androidx.work:work-rxjava2:${AndroidSdkVersions.workManagerVersion}"
    const val constraintLayout = "com.android.support.constraint:constraint-layout:${AndroidSdkVersions.constraintVersion}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${AndroidSdkVersions.recyclerViewVersion}"
}

object TestLibs {
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

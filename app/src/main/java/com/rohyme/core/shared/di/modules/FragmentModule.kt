package com.rohyme.core.shared.di.modules

import com.rohyme.core.presentationLayer.features.splashScreen.SplashScreen
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeSplashScreen(): SplashScreen

}
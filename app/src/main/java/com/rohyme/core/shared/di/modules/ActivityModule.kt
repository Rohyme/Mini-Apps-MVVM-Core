package com.rohyme.core.shared.di.modules

import com.rohyme.core.presentationLayer.features.mainScreen.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity():MainActivity
}
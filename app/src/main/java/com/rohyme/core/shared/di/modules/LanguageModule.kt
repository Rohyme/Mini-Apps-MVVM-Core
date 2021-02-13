package com.rohyme.core.shared.di.modules

import com.rohyme.core.shared.appUtils.deviceUtils.SharedPreferenceUtil
import com.rohyme.core.shared.appUtils.deviceUtils.language.LanguageUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object LanguageModule {

    @Provides
    fun provideLanguageUtils(preference: SharedPreferenceUtil) : LanguageUtils {
        return LanguageUtils(preference)
    }

}
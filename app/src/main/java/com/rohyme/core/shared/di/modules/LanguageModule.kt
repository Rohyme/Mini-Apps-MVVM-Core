package com.rohyme.core.shared.di.modules

import com.rohyme.core.shared.appUtils.deviceUtils.SharedPreferenceUtil
import com.rohyme.core.shared.appUtils.deviceUtils.language.LanguageUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object LanguageModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideLanguageUtils(preference: SharedPreferenceUtil) : LanguageUtils {
        return LanguageUtils(preference)
    }

}
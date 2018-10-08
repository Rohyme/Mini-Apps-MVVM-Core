package com.binarycase.saudiassociation.di.modules

import com.binarycase.saudiassociation.appUtils.deviceUtils.LanguageUtils
import com.binarycase.saudiassociation.appUtils.deviceUtils.SharedPreferenceUtil
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LanguageModule {

    @Provides
    @Singleton
    fun provideLanguageUtils(preference: SharedPreferenceUtil) : LanguageUtils {
        return LanguageUtils(preference)
    }

}
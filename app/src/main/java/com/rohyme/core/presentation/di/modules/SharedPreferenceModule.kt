package com.rohyme.core.presentation.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.rohyme.core.presentation.appUtils.otherUtils.Constants
import com.rohyme.core.presentation.appUtils.deviceUtils.SharedPreferenceUtil
import com.rohyme.core.presentation.myApp.MyApplication

import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class SharedPreferenceModule {

    @Provides
    @Singleton
    fun provideSharedPreference(app: MyApplication): SharedPreferences {
        return app.getSharedPreferences(Constants.SHARED_PREFERENCE, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSharedPreferenceUtil(sharedPreference: SharedPreferences, gson: Gson): SharedPreferenceUtil {
        return SharedPreferenceUtil(sharedPreference, gson)
    }

}
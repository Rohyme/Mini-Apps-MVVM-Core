package com.binarycase.saudiassociation.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.binarycase.saudiassociation.myApp.MyApplication
import com.binarycase.saudiassociation.appUtils.Constants
import com.binarycase.saudiassociation.appUtils.deviceUtils.SharedPreferenceUtil
import com.google.gson.Gson
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
  fun provideSharedPreferenceUtil(sharedPreference: SharedPreferences ,gson : Gson): SharedPreferenceUtil {
    return SharedPreferenceUtil(sharedPreference, gson)
  }
}
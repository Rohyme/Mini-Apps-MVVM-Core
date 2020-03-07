package com.rohyme.core.shared.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.rohyme.core.shared.myApp.MyApplication
import com.rohyme.core.shared.appUtils.Constants
import com.rohyme.core.shared.appUtils.deviceUtils.SharedPreferenceUtil
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object SharedPreferenceModule {
  @Singleton
  @Provides
  fun provideSharedPreference(app: MyApplication): SharedPreferences {
    return app.getSharedPreferences(Constants.SHARED_PREFERENCE, Context.MODE_PRIVATE)
  }

  @Singleton
  @Provides
  @JvmStatic
  fun provideSharedPreferenceUtil(sharedPreference: SharedPreferences ,moshi : Moshi): SharedPreferenceUtil {
    return SharedPreferenceUtil(sharedPreference, moshi)
  }
}
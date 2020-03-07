package com.rohyme.core.shared.di.modules

import android.content.Context
import com.rohyme.core.shared.di.qualifiers.ApplicationContext
import com.rohyme.core.shared.myApp.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ApplicationModule {

  @JvmStatic
  @Provides
  @Singleton
  @ApplicationContext
  fun applicationContext(app: MyApplication): Context {
    return app.applicationContext
  }

}
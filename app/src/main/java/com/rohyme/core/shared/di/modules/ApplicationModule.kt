package com.rohyme.core.shared.di.modules

import android.content.Context
import com.rohyme.core.shared.myApp.MyApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
object ApplicationModule {

  @Provides
  fun applicationContext(app: MyApplication): Context {
    return app.applicationContext
  }

}
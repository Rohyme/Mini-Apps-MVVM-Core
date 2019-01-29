package com.rohyme.core.presentation.di.modules

import android.content.Context
import com.rohyme.core.presentation.di.qualifiers.ForApplication
import com.rohyme.core.presentation.myApp.MyApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

  @Provides
  @ForApplication
  fun applicationContext(app: MyApplication): Context {
    return app.applicationContext
  }

}
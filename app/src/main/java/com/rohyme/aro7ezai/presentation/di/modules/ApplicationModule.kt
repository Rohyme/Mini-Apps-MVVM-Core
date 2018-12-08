package com.rohyme.aro7ezai.presentation.di.modules

import android.content.Context
import com.rohyme.aro7ezai.presentation.di.qualifiers.ForApplication
import com.rohyme.aro7ezai.presentation.myApp.MyApplication
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
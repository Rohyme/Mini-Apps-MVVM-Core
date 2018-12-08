package com.rohyme.aro7ezai.presentation.di.components

import com.rohyme.aro7ezai.presentation.di.modules.ApplicationModule
import com.rohyme.aro7ezai.presentation.di.modules.DataBaseModule
import com.rohyme.aro7ezai.presentation.di.modules.NetworkModule
import com.rohyme.aro7ezai.presentation.di.modules.ViewModelModule
import com.rohyme.aro7ezai.presentation.myApp.MyApplication
import com.rohyme.aro7ezai.presentation.ui.base.BaseActivityWithInjector
import com.rohyme.aro7ezai.presentation.ui.base.BaseFragmentWithInjector
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApplicationModule::class, NetworkModule::class, ViewModelModule::class , DataBaseModule::class]
)
interface NetworkComponent {
  fun inject(activity: BaseActivityWithInjector)
  fun inject(fragment: BaseFragmentWithInjector)

  @Component.Builder
  interface NetworkBuilder {
    fun builder(): NetworkComponent
    @BindsInstance
    fun application(app: MyApplication): NetworkBuilder
    @BindsInstance
    fun baseUrl(baseUrl: String): NetworkBuilder
  }
}
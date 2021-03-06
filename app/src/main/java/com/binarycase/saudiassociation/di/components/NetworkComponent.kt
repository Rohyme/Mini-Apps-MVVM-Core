package com.binarycase.saudiassociation.di.components

import com.binarycase.saudiassociation.di.modules.*
import com.binarycase.saudiassociation.myApp.MyApplication
import com.binarycase.saudiassociation.ui.base.BaseActivityWithInjector
import com.binarycase.saudiassociation.ui.base.BaseFragmentWithInjector
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApplicationModule::class, NetworkModule::class, ViewModelModule::class, SharedPreferenceModule::class , LanguageModule::class]
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
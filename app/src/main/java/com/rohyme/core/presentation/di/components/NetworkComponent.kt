package com.rohyme.core.presentation.di.components

import com.rohyme.core.presentation.di.modules.*
import com.rohyme.core.presentation.myApp.MyApplication
import com.rohyme.core.presentation.ui.base.BaseActivityWithInjector
import com.rohyme.core.presentation.ui.base.BaseFragmentWithInjector
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [ApplicationModule::class, NetworkModule::class, ViewModelModule::class, SharedPreferenceModule::class]
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
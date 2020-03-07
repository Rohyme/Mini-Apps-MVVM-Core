package com.rohyme.core.shared.di.components

import com.rohyme.core.shared.di.modules.*
import com.rohyme.core.shared.di.qualifiers.ForApplication
import com.rohyme.core.shared.myApp.MyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class
    , ApplicationModule::class
    , ActivityModule::class
    , NetworkModule::class
    , MoshiModule::class
    , CoreDataModule::class
    , SharedPreferenceModule::class
    , LanguageModule::class
    , ViewModelModule::class
])
interface ApplicationComponent {
    fun injectApp(myApp: MyApplication)
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance appContext: MyApplication): ApplicationComponent
    }
}
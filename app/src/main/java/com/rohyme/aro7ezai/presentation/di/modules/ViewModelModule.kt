package com.rohyme.aro7ezai.presentation.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.rohyme.aro7ezai.presentation.ui.screens.addressDetailsScreen.AddressDetailsViewModel
import com.rohyme.aro7ezai.presentation.ui.screens.mainScreen.MainScreenVM
import com.rohyme.aro7ezai.presentation.ui.screens.searchFragment.SearchFragmentViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModels[modelClass]?.get() as T
}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

  @Binds
  internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelKey(MainScreenVM::class)
  internal abstract fun MainScreenVM(viewModel: MainScreenVM): ViewModel


  @Binds
  @IntoMap
  @ViewModelKey(SearchFragmentViewModel::class)
  internal abstract fun SearchFragmentViewModel(viewModel: SearchFragmentViewModel): ViewModel


  @Binds
  @IntoMap
  @ViewModelKey(AddressDetailsViewModel::class)
  internal abstract fun AddressDetailsViewModel(viewModel: AddressDetailsViewModel): ViewModel

}
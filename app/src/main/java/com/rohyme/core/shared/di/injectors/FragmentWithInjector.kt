package com.rohyme.core.shared.di.injectors

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rohyme.core.shared.di.modules.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class FragmentWithInjector<T : ViewModel> : Fragment() {
    abstract val viewModelClass: Class<T>
    lateinit var vm: T
    @Inject
    lateinit var factory: ViewModelFactory

    fun injectVM(f: Fragment) {
        AndroidSupportInjection.inject(f)
        vm = ViewModelProvider(f, factory).get(viewModelClass)
    }

}
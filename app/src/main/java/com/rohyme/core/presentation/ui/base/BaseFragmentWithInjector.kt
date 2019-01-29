package com.rohyme.core.presentation.ui.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import com.rohyme.core.presentation.di.modules.ViewModelFactory
import com.rohyme.core.presentation.myApp.MyApplication
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseFragmentWithInjector : Fragment() {
    @Inject
    lateinit var factory: ViewModelFactory



    lateinit var vm: ViewModel
    lateinit var disposal: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as MyApplication).networkComponent.inject(this)
        vm = ViewModelProviders.of(this, factory)[getFragmentVM()]
    }

    abstract fun getFragmentVM(): Class<out ViewModel>


}
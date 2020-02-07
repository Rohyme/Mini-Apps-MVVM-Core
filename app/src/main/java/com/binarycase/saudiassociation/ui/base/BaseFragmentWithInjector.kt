package com.binarycase.saudiassociation.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.binarycase.saudiassociation.appUtils.deviceUtils.LanguageUtils
import com.binarycase.saudiassociation.di.modules.ViewModelFactory
import com.binarycase.saudiassociation.myApp.MyApplication
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseFragmentWithInjector : Fragment() {
    @Inject
    lateinit var factory: ViewModelFactory

    @Inject
    lateinit var languageUtils: LanguageUtils


    lateinit var vm: ViewModel
    lateinit var disposal: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as MyApplication).networkComponent.inject(this)
        vm = ViewModelProviders.of(this, factory)[getActivityVM()]
    }

    abstract fun getActivityVM(): Class<out ViewModel>


}
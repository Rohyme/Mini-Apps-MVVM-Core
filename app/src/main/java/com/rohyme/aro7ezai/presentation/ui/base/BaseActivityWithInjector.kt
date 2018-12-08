package com.rohyme.aro7ezai.presentation.ui.base

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.multidex.MultiDex
import android.support.v7.app.AppCompatActivity
import com.rohyme.aro7ezai.presentation.appUtils.networkUtils.NetworkUtils
import com.rohyme.aro7ezai.presentation.di.modules.ViewModelFactory
import com.rohyme.aro7ezai.presentation.myApp.MyApplication
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import javax.inject.Inject

abstract class BaseActivityWithInjector : AppCompatActivity(), NetworkUtils.ConnectionStatusCB {
    @Inject
    lateinit var factory: ViewModelFactory


    lateinit var vm : ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApplication).networkComponent.inject(this)
        vm = ViewModelProviders.of(this, factory)[getActivityVM()]
        onConnectionChanged()

    }



    override fun attachBaseContext(newBase: Context?) {
        MultiDex.install(this)
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    abstract fun getActivityVM(): Class<out ViewModel>


    override fun onConnected() {
    }

    override fun onDisconnected() {
    }

    @SuppressLint("CheckResult")
    fun onConnectionChanged() {
        NetworkUtils.getNetworkUtils().getNetworkStatus().subscribe {
            when (it) {
                NetworkUtils.CONNECTED -> {
                    onConnected()
                }
                NetworkUtils.DISCONNECTED -> {
                    onDisconnected()
                }
            }
        }
    }

}
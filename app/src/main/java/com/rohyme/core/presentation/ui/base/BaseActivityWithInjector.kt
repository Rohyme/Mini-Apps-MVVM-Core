package com.rohyme.core.presentation.ui.base

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.multidex.MultiDex
import android.support.v7.app.AppCompatActivity
import com.rohyme.core.presentation.appUtils.languageUtils.LanguageUtils
import com.rohyme.core.presentation.appUtils.languageUtils.LocalizationUtils
import com.rohyme.core.presentation.appUtils.networkUtils.NetworkUtils
import com.rohyme.core.presentation.di.modules.ViewModelFactory
import com.rohyme.core.presentation.myApp.MyApplication
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import javax.inject.Inject

abstract class BaseActivityWithInjector : AppCompatActivity(), NetworkUtils.ConnectionStatusCB {
    @Inject
    lateinit var factory: ViewModelFactory

    @Inject
    lateinit var language: LanguageUtils
    lateinit var vm: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApplication).networkComponent.inject(this)
        vm = ViewModelProviders.of(this, factory)[getActivityVM()]
        onConnectionChanged()
    }

    override fun attachBaseContext(newBase: Context?) {
        val sharedPref = newBase!!.getSharedPreferences("Shared Prefe", Context.MODE_PRIVATE)
        val languageContext = LocalizationUtils().setLocale(newBase, sharedPref.getString("language","ar")!!)
        MultiDex.install(this)
        super.attachBaseContext(CalligraphyContextWrapper.wrap(languageContext))
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
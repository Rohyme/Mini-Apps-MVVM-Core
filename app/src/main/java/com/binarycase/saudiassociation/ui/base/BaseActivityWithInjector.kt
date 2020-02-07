package com.binarycase.saudiassociation.ui.base

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import androidx.databinding.Observable
import android.os.Bundle
import androidx.multidex.MultiDex
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.binarycase.saudiassociation.appUtils.deviceUtils.LanguageUtils
import com.binarycase.saudiassociation.appUtils.networkUtils.NetworkUtils
import com.binarycase.saudiassociation.myApp.MyApplication
import com.binarycase.saudiassociation.di.modules.ViewModelFactory
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import javax.inject.Inject

abstract class BaseActivityWithInjector : AppCompatActivity(), NetworkUtils.ConnectionStatusCB {
    @Inject
    lateinit var factory: ViewModelFactory

    @Inject
    lateinit var languageUtils: LanguageUtils
    lateinit var vm: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApplication).networkComponent.inject(this)
        vm = ViewModelProviders.of(this, factory)[getActivityVM()]
        onLanguageChanged()
        onConnectionChanged()

    }

    private fun onLanguageChanged() {
        if (!this::languageUtils.isInitialized) {
            Log.e("BASE Activity Tag", "Language Module isn't intialized")
            return
        }
        languageUtils.isArabic.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                languageUtils.setLocale(this@BaseActivityWithInjector, languageUtils.getLocality()!!)
                recreate()
            }
        })
    }


    override fun attachBaseContext(newBase: Context?) {
        MultiDex.install(this)
        val  context = if (this::languageUtils.isInitialized) languageUtils.attach(newBase!!)  else newBase
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context))
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
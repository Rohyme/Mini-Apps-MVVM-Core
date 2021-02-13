package com.rohyme.core.shared.myApp

import android.app.Application
import android.content.IntentFilter
import android.net.ConnectivityManager
import com.rohyme.core.R
import com.rohyme.core.shared.appUtils.StateConstants.BUTTON_ERROR
import com.rohyme.core.shared.appUtils.StateConstants.BUTTON_LOADING
import com.rohyme.core.shared.appUtils.networkUtils.ConnectivityReciever
import com.tripl3dev.prettystates.StatesConfigFactory
import dagger.hilt.android.HiltAndroidApp
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

@HiltAndroidApp
class MyApplication : Application() {
    private lateinit var networkDetector: ConnectivityReciever

    override fun onCreate() {
        super.onCreate()
        networkDetector = ConnectivityReciever()
        val intent = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkDetector, intent)

        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Cairo-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )

    StatesConfigFactory.intialize()
            .initDefaultViews()
    StatesConfigFactory.get()
            .setDefaultEmptyLayout(R.layout.state_view_empty)
            .setDefaultErrorLayout(R.layout.state_view_error)
            .addStateView(BUTTON_ERROR,R.layout.state_view_button_error)
    StatesConfigFactory.get().addStateView(BUTTON_LOADING,R.layout.state_view_button_loading)
    }

    override fun onTerminate() {
        super.onTerminate()
        networkDetector.abortBroadcast()
    }
}
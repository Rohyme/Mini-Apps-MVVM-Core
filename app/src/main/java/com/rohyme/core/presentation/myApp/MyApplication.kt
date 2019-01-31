package com.rohyme.core.presentation.myApp

import android.app.Application
import android.content.IntentFilter
import android.net.ConnectivityManager
import com.rohyme.core.R
import com.rohyme.core.presentation.appUtils.AssetsUtils.AssetsFiles
import com.rohyme.core.presentation.appUtils.networkUtils.ConnectivityReciever
import com.rohyme.core.presentation.appUtils.otherUtils.Constants
import com.rohyme.core.presentation.appUtils.otherUtils.StateConstants.Companion.BUTTON_ERROR
import com.rohyme.core.presentation.appUtils.otherUtils.StateConstants.Companion.BUTTON_LOADING
import com.rohyme.core.presentation.di.components.ApplicationComponent
import com.rohyme.core.presentation.di.components.DaggerApplicationComponent
import com.rohyme.core.presentation.di.components.DaggerNetworkComponent
import com.rohyme.core.presentation.di.components.NetworkComponent
import com.tripl3dev.prettystates.StatesConfigFactory
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class MyApplication : Application(){
private lateinit var appComponent: ApplicationComponent
lateinit var networkComponent: NetworkComponent
  private lateinit var networkDetector: ConnectivityReciever


  override fun onCreate() {
  super.onCreate()
  appComponent = DaggerApplicationComponent.builder()
      .applicationContext(this)
      .builder()

  networkComponent = DaggerNetworkComponent.builder()
      .application(this)
      .baseUrl(Constants.BASE_URL)
      .builder()

  networkDetector = ConnectivityReciever()
  val intent = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
  registerReceiver(networkDetector, intent)

    CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                    .setDefaultFontPath(AssetsFiles.REGULAR_FONT)
                    .setFontAttrId(R.attr.fontPath)
                    .build())
    StatesConfigFactory.intialize()
            .initDefaultViews()
    StatesConfigFactory.get()
            .addStateView(BUTTON_ERROR,R.layout.state_view_button_error)
    StatesConfigFactory.get().addStateView(BUTTON_LOADING,R.layout.state_view_button_loading)
}
  override fun onTerminate() {
    super.onTerminate()
    networkDetector.abortBroadcast()
  }
}
package com.binarycase.saudiassociation.myApp

import android.app.Application
import android.content.IntentFilter
import android.net.ConnectivityManager
import com.binarycase.saudiassociation.R
import com.binarycase.saudiassociation.di.components.ApplicationComponent
import com.binarycase.saudiassociation.di.components.NetworkComponent
import com.binarycase.saudiassociation.appUtils.Constants
import com.binarycase.saudiassociation.appUtils.StateConstants.Companion.BUTTON_ERROR
import com.binarycase.saudiassociation.appUtils.StateConstants.Companion.BUTTON_LOADING
import com.binarycase.saudiassociation.appUtils.networkUtils.ConnectivityReciever
import com.binarycase.saudiassociation.di.components.DaggerApplicationComponent
import com.binarycase.saudiassociation.di.components.DaggerNetworkComponent
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
                    .setDefaultFontPath("fonts/Cairo-Regular.ttf")
                    .setFontAttrId(R.attr.fontPath)
                    .build())

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
package com.rohyme.aro7ezai.presentation.myApp

import android.app.Application
import android.content.IntentFilter
import android.net.ConnectivityManager
import com.rohyme.aro7ezai.R
import com.rohyme.aro7ezai.presentation.appUtils.Constants
import com.rohyme.aro7ezai.presentation.appUtils.StateConstants.Companion.BUTTON_ERROR
import com.rohyme.aro7ezai.presentation.appUtils.StateConstants.Companion.BUTTON_LOADING
import com.rohyme.aro7ezai.presentation.appUtils.networkUtils.ConnectivityReciever
import com.rohyme.aro7ezai.presentation.di.components.ApplicationComponent
import com.rohyme.aro7ezai.presentation.di.components.DaggerApplicationComponent
import com.rohyme.aro7ezai.presentation.di.components.DaggerNetworkComponent
import com.rohyme.aro7ezai.presentation.di.components.NetworkComponent
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
                    .setDefaultFontPath("fonts/DroidKufi-Regular.ttf")
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
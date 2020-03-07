package com.rohyme.core.shared.appUtils.networkUtils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rohyme.core.shared.appUtils.ConnectionStatus

object NetworkUtils  {

  private val _networkStatus = MutableLiveData<ConnectionStatus>()
  val networkStatus : LiveData<ConnectionStatus> = _networkStatus



  fun setNetworkStatus(newNetworkStatus: ConnectionStatus) {
    _networkStatus.postValue(newNetworkStatus)
  }

  interface ConnectionStatusCB {
    fun onConnected() {}
    fun onDisconnected() {}
  }
}
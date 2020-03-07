package com.rohyme.core.shared.appUtils.networkUtils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.rohyme.core.shared.appUtils.ConnectionStatus

class ConnectivityReciever :BroadcastReceiver() {
  override fun onReceive(context: Context?, intent: Intent?) {
    if (context?.checkNetworkState() == true){
      NetworkUtils.setNetworkStatus(ConnectionStatus.CONNECTED)
    }else{
      NetworkUtils.setNetworkStatus(ConnectionStatus.NOTCONNECTED)
    }
  }

  private fun Context.checkNetworkState(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      val nw      = connectivityManager.activeNetwork ?: return false
      val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
      return when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
      }
    } else {
      val nwInfo = connectivityManager.activeNetworkInfo ?: return false
      return nwInfo.isConnected
    }
  }
}
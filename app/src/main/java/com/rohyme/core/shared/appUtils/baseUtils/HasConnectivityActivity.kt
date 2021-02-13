package com.rohyme.core.shared.appUtils.baseUtils

import com.rohyme.core.shared.appUtils.networkUtils.NetworkUtils

interface HasConnectivityActivity : NetworkUtils.ConnectionStatusCB
interface HasConnectivityFragment : NetworkUtils.ConnectionStatusCB {
    fun useFurtherConnectionBehavior() = true
}
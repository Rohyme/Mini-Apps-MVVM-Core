package com.rohyme.core.shared.appUtils

import com.rohyme.core.BuildConfig

object Constants {
    const val BASE_URL = BuildConfig.BASE_URL
    const val IMG_BASEURL = ""
    const val SHARED_PREFERENCE = "SHARED_PREFERENCE_CONSTANT"
}

object StateConstants {
    const val BUTTON_LOADING = 30
    const val BUTTON_ERROR = 30
}

object SharedPreferences {
    const val SP_LANGUAGE="language"
}

enum class ConnectionStatus{
    CONNECTED,
    NOTCONNECTED
}
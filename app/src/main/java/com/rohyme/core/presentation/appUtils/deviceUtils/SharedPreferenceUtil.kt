package com.rohyme.core.presentation.appUtils.deviceUtils

import android.content.SharedPreferences
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

class SharedPreferenceUtil @Inject constructor(val sharedPreference: SharedPreferences, gson: Gson) {

    var language: String?
        get() = this.getString("language", "ar")
        set(currentLanguage) = putString("language", currentLanguage!!)

    fun putString(key: String, value: String?) {
        val editor = sharedPreference.edit()
        editor.putString(key, value)
        editor.apply()
    }


    fun getString(key: String, defaultValue: String?): String? {
        return sharedPreference.getString(key, defaultValue)
    }


}

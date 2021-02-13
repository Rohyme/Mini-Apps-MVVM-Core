package com.rohyme.core.shared.appUtils.deviceUtils

import android.content.SharedPreferences
import com.rohyme.core.shared.appUtils.SharedPreferences.SP_LANGUAGE
import com.rohyme.core.shared.appUtils.deviceUtils.language.Languages
import com.squareup.moshi.Moshi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferenceUtil @Inject constructor(private val sharedPreference: SharedPreferences, moshi: Moshi) {

  var language: Languages
    get() = Languages.valueOf(this.getString(SP_LANGUAGE,Languages.ARABIC.value)?:Languages.ARABIC.value)
    set(currentLanguage) = putString(SP_LANGUAGE, currentLanguage.value)



  private fun putString(key: String, value: String?) {
    val editor = sharedPreference.edit()
    editor.putString(key, value)
    editor.apply()
  }


  private fun getString(key: String, defaultValue: String?): String? {
    return sharedPreference.getString(key, defaultValue)
  }


}

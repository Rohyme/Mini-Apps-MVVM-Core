package com.rohyme.core.presentation.appUtils.AssetsUtils

import android.content.Context
import com.google.gson.Gson
class AssetUtils{
companion object {
    inline fun <reified T> getData(context: Context, fileName: String): T {
        val json = context.assets.open("$fileName.json").bufferedReader().use {
            it.readText()
        }
        return Gson().fromJson(json, T::class.java)
    }
}
}
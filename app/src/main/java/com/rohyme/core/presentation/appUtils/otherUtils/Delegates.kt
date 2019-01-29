package com.rohyme.core.presentation.appUtils.otherUtils

import android.app.Activity
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import kotlin.reflect.KProperty

class SetContentView<in R : Activity, out b : ViewDataBinding>(@LayoutRes private val layoutRes: Int) {
    private var value: b? = null

    operator fun getValue(thisRef: Activity, property: KProperty<*>): b {
        if (value == null) {
            value = DataBindingUtil.setContentView(thisRef, layoutRes)
        }
        return value!!
    }
}


fun <A : Activity, B : ViewDataBinding> setContent(@LayoutRes layoutRes: Int): SetContentView<A, B> {
    return SetContentView(layoutRes)
}
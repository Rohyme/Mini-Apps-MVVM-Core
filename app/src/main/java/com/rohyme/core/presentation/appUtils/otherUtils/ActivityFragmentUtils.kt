package com.rohyme.core.presentation.appUtils.otherUtils

import android.app.Activity
import android.content.Intent
import android.support.v4.app.Fragment

fun Activity.restartActivity() {
    startActivity(Intent(this, this.javaClass))
    finish()
}
fun Fragment.restartActivity() {
    startActivity(Intent(activity, activity!!.javaClass))
    activity!!.finish()
}



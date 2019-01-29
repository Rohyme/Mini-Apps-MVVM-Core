package com.rohyme.core.presentation.appUtils.viewUtils

import android.databinding.BindingAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

// Created by Rohyme on 10/23/2018.


@BindingAdapter("date:getYear")
fun TextView.getYear(date: String){
    this.text = date.substring(0,4)
}

@BindingAdapter("num:setNum")
fun TextView.setNum(num :Int){
    text = addZero(num)
}

@BindingAdapter("image:setImgFromDrawanle")
fun ImageView.setDrawable(drawableName: String) {
  val name =   drawableName.substringBefore(".")
    val imageId = resources.getIdentifier(name, "drawable", context.packageName)
        Picasso.get().load(imageId).into(this)
}

fun addZero(number: Int): String {
    val sb = StringBuilder()
    val num = number.toString()
    if (num.length == 1) {
        sb.append('0')
    }
    sb.append(number)
    return sb.toString()
}
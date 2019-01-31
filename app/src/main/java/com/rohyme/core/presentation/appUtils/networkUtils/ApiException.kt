package com.rohyme.core.presentation.appUtils.networkUtils

class ApiException(val status:Int,val errorMessage:String): Exception() {
}
package com.rohyme.core.presentation.appUtils.networkUtils

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GenericResponse<T> {
    @SerializedName(value = "Status", alternate = ["status"])
    @Expose
    var status: Int = 0
    @SerializedName(value = "Message", alternate = ["message"])
    @Expose
    var message: String? = null
    @SerializedName(value = "Code", alternate = ["code"])
    @Expose
    var code: Int = 0
    @SerializedName(value = "Body", alternate = ["body"])
    @Expose
    var body: T? = null
}

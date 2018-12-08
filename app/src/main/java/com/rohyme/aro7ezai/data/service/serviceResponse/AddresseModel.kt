package com.rohyme.aro7ezai.data.service.serviceResponse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class AddresseModel() : Serializable {
    @SerializedName("address")
    var address: String = ""
    @SerializedName("id")
    var id: String = ""
    @SerializedName("status")
    var status: String = ""
}

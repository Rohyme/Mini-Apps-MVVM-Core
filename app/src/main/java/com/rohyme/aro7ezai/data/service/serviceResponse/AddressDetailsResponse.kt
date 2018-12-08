package com.rohyme.aro7ezai.data.service.serviceResponse

import com.google.gson.annotations.SerializedName

data class AddressDetailsResponse(
        @SerializedName("addressDetails")
        var addressDetails: List<AddressDetail> = listOf(),
        @SerializedName("addressFrom")
        var addressFrom: String = "",
        @SerializedName("addressTo")
        var addressTo: String = "",
        @SerializedName("address_from")
        var addressFromId: String = "",
        @SerializedName("address_to")
        var addressToId: String = "",
        @SerializedName("id")
        var id: String = "",
        @SerializedName("opdate")
        var opdate: String = "",
        @SerializedName("status")
        var status: String = ""
) {
    data class AddressDetail(
            @SerializedName("address")
            var address: String = "",
            @SerializedName("aro7_id")
            var aro7Id: String = "",
            @SerializedName("id")
            var id: String = "",
            @SerializedName("opdate")
            var opdate: String = "",
            @SerializedName("status")
            var status: String = ""
    )
}
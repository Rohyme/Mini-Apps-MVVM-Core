package com.rohyme.aro7ezai.data.service

import com.rohyme.aro7ezai.data.service.serviceResponse.AddressDetailsResponse
import com.rohyme.aro7ezai.data.service.serviceResponse.AddresseModel
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {


@GET("address.php")
fun getAddresses():Single<List<AddresseModel>>


    @GET("aro7.php")
    fun getAddressDetails(@Query("from") fromId: String?, @Query("to") toId: String): Single<List<AddressDetailsResponse>>
}
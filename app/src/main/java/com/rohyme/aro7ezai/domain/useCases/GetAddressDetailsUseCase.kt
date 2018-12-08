package com.rohyme.aro7ezai.domain.useCases

import com.rohyme.aro7ezai.data.service.ApiService
import com.rohyme.aro7ezai.data.service.serviceRequest.AddressDetailsRequest
import com.rohyme.aro7ezai.data.service.serviceResponse.AddressDetailsResponse
import com.rohyme.aro7ezai.domain.interactors.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetAddressDetailsUseCase @Inject constructor(private val api :ApiService) : SingleUseCase<AddressDetailsResponse, AddressDetailsRequest>() {
    override fun buildUseCaseObservable(params: AddressDetailsRequest?): Single<AddressDetailsResponse> {
    return api.getAddressDetails(params!!.placeFromId,params.placeToId).map {
        it[0]
    }
    }
}
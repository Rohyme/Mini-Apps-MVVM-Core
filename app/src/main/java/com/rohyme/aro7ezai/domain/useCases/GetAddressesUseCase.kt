package com.rohyme.aro7ezai.domain.useCases

import com.rohyme.aro7ezai.data.service.ApiService
import com.rohyme.aro7ezai.data.service.serviceResponse.AddresseModel
import com.rohyme.aro7ezai.domain.interactors.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetAddressesUseCase @Inject constructor(val api:ApiService): SingleUseCase<List<AddresseModel>, Void>() {
    override fun buildUseCaseObservable(params: Void?): Single<List<AddresseModel>> {
        return api.getAddresses()
    }
}
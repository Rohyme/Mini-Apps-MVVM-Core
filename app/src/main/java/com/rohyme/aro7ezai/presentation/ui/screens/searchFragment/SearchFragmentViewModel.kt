package com.rohyme.aro7ezai.presentation.ui.screens.searchFragment

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.rohyme.aro7ezai.data.service.serviceRequest.AddressDetailsRequest
import com.rohyme.aro7ezai.data.service.serviceResponse.AddressDetailsResponse
import com.rohyme.aro7ezai.domain.useCases.GetAddressDetailsUseCase
import com.rohyme.aro7ezai.presentation.appUtils.SingleEventLiveData
import com.rohyme.aro7ezai.presentation.appUtils.StateView
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import java.lang.IndexOutOfBoundsException
import javax.inject.Inject

class SearchFragmentViewModel @Inject constructor(val useCase : GetAddressDetailsUseCase):ViewModel() {

 private val addressDetailsObs = SingleEventLiveData<StateView>()

    fun  getAddressObs():LiveData<StateView>{
        return addressDetailsObs
    }

    fun fetchAddressDetails(startId: String, endId: String) {
        useCase.execute(object :SingleObserver<AddressDetailsResponse>{
            override fun onSuccess(t: AddressDetailsResponse) {
                addressDetailsObs.postValue(StateView.Success(t))
            }

            override fun onSubscribe(d: Disposable) {
                addressDetailsObs.postValue(StateView.Loading)
            }

            override fun onError(e: Throwable) {
                if (e is IndexOutOfBoundsException){
                    addressDetailsObs.postValue(StateView.Empty)
                }else{
                    addressDetailsObs.postValue(StateView.Errors(e))
                }
            }

        }, AddressDetailsRequest(endId,if (startId=="0")null else startId))
    }
}
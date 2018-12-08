package com.rohyme.aro7ezai.presentation.ui.screens.mainScreen

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.rohyme.aro7ezai.data.service.serviceResponse.AddresseModel
import com.rohyme.aro7ezai.domain.useCases.GetAddressesUseCase
import com.rohyme.aro7ezai.presentation.appUtils.SingleEventLiveData
import com.rohyme.aro7ezai.presentation.appUtils.StateView
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainScreenVM @Inject constructor(val useCase : GetAddressesUseCase): ViewModel() {
    private var addressesObs : SingleEventLiveData<StateView> = SingleEventLiveData()

    fun getAddressesObs() :LiveData<StateView>{
    return addressesObs
    }

    fun fetchAddresses(){
       useCase.execute(object :SingleObserver<List<AddresseModel>>{
           override fun onSuccess(t: List<AddresseModel>) {
               addressesObs.postValue(StateView.Success(t))
           }

           override fun onSubscribe(d: Disposable) {
               addressesObs.postValue(StateView.Loading)
           }

           override fun onError(e: Throwable) {
               addressesObs.postValue(StateView.Errors(e))
           }

       })
    }

    fun retry() {
        useCase.retry()
    }


}
package com.rohyme.aro7ezai.presentation.ui.screens.mainScreen

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.os.Bundle
import com.rohyme.aro7ezai.R
import com.rohyme.aro7ezai.data.service.serviceResponse.AddressDetailsResponse
import com.rohyme.aro7ezai.data.service.serviceResponse.AddresseModel
import com.rohyme.aro7ezai.presentation.appUtils.StateView
import com.rohyme.aro7ezai.presentation.ui.base.BaseActivityWithInjector
import com.rohyme.aro7ezai.presentation.ui.screens.addressDetailsScreen.AddressDetailsFragment
import com.rohyme.aro7ezai.presentation.ui.screens.newAddress.AddNewAddressFragment
import com.rohyme.aro7ezai.presentation.ui.screens.searchFragment.SearchFragment
import com.tripl3dev.prettystates.StatesConstants
import com.tripl3dev.prettystates.setState
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : BaseActivityWithInjector() {

    lateinit var mainViewModel: MainScreenVM

    override fun getActivityVM(): Class<out ViewModel> {
        return MainScreenVM::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = vm as MainScreenVM
        mainViewModel.fetchAddresses()
        mainViewModel.getAddressesObs().observe(this@MainActivity, Observer {
            when (it) {
                is StateView.Success<*> -> {
                    fragmentContainer.setState(StatesConstants.NORMAL_STATE)

                    val data = it.data as List<AddresseModel>
                    val searchFragment = SearchFragment.instance(data)
                    supportFragmentManager.beginTransaction().addToBackStack(null).add(R.id.fragmentContainer, searchFragment).commit()

//                    FragmentUtils.replace(supportFragmentManager,)
                }

                is StateView.Loading -> {
                    fragmentContainer.setState(StatesConstants.LOADING_STATE)
                }

                is StateView.Errors -> {
                    fragmentContainer.setState(StatesConstants.ERROR_STATE)
                            .setOnClickListener {
                                mainViewModel.retry()
                            }
                }
            }
        })
    }

    fun openDetailsFragment(data: AddressDetailsResponse) {
        val addressesString = data.addressDetails.map {
            it.address
        }
        val detailsFragment = AddressDetailsFragment.instance(ArrayList(addressesString))
        supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, detailsFragment).commit()
    }

    fun openNewAddressFragment() {
        supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, AddNewAddressFragment()).commit()

    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (fragment == null || fragment is SearchFragment) {
            finish()
            return
        }else{
            super.onBackPressed()
        }

    }


}

package com.rohyme.aro7ezai.presentation.ui.screens.searchFragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.annotation.StringRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.blankj.utilcode.util.ToastUtils
import com.rohyme.aro7ezai.R
import com.rohyme.aro7ezai.data.service.serviceResponse.AddressDetailsResponse
import com.rohyme.aro7ezai.data.service.serviceResponse.AddresseModel
import com.rohyme.aro7ezai.data.service.serviceResponse.AddressesListClasse
import com.rohyme.aro7ezai.presentation.appUtils.StateConstants
import com.rohyme.aro7ezai.presentation.appUtils.StateView
import com.rohyme.aro7ezai.presentation.ui.base.BaseFragmentWithInjector
import com.rohyme.aro7ezai.presentation.ui.screens.mainScreen.MainActivity
import com.piotrek.customspinner.CustomSpinner
import com.tripl3dev.prettystates.StatesConstants
import com.tripl3dev.prettystates.setState
import kotlinx.android.synthetic.main.search_fragment.*

class SearchFragment : BaseFragmentWithInjector() {

    lateinit var searchViewModel: SearchFragmentViewModel
    var startId = ""
    var endId = ""
    var addressesList :ArrayList<AddresseModel> = ArrayList()

    companion object {
        val ADDRESSES_LIST ="ADDRESSES_LIST"
        fun instance(listAddresses : List<AddresseModel>) = SearchFragment().apply {
            arguments = Bundle().apply {
                putSerializable(ADDRESSES_LIST , AddressesListClasse(listAddresses))
            }
        }
    }

    override fun getFragmentVM(): Class<out ViewModel> {
        return SearchFragmentViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addressesList = (arguments!!.getSerializable(ADDRESSES_LIST) as AddressesListClasse).addresses
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel = vm as SearchFragmentViewModel
        val labels = addressesList.map {
            it.address
        }

        startTo.setValues(labels.toTypedArray(), R.string.start_place, true)
        endTo.setValues(labels.toTypedArray(), R.string.end_place, false)
        searchBt.setOnClickListener {
            if (endId == "0") {
                ToastUtils.showShort("من فضلك قم باختيار المكان المراد الذهاب اليه")
                return@setOnClickListener
            }
            searchViewModel.fetchAddressDetails(startId, endId)
        }

        newAddress.setOnClickListener {
            (activity as MainActivity).openNewAddressFragment()

        }

        searchViewModel.getAddressObs().observe(this@SearchFragment, Observer {
            when(it){
                is StateView.Success<*> ->{
                    searchBt.setState(StatesConstants.NORMAL_STATE)
                    val data = it.data as List<AddressDetailsResponse>
                    (activity as MainActivity).openDetailsFragment(data)
                }
                is StateView.Loading ->{
                    searchBt.setState(StateConstants.BUTTON_LOADING)
                }
                is StateView.Empty ->{
                    searchBt.setState(StatesConstants.NORMAL_STATE)
                    (activity as MainActivity).openDetailsFragment(listOf())
                }
                is StateView.Errors ->{
                    searchBt.setState(StatesConstants.NORMAL_STATE)
                    ToastUtils.showShort("حدث خطأ ما من فضلك حاول مرة اخري")
                }
            }
        })

    }


    fun CustomSpinner.setValues(items: Array<String>, @StringRes hint: Int, isStart: Boolean) {
        initializeStringValues(items, resources.getString(hint))
        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (isStart) startId = position.toString()
                else endId = position.toString()
            }
        }

    }


}
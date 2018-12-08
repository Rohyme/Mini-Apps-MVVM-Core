package com.rohyme.aro7ezai.presentation.ui.screens.addressDetailsScreen

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rohyme.aro7ezai.R
import com.rohyme.aro7ezai.presentation.ui.base.BaseFragmentWithInjector
import com.tripl3dev.luffyyview.baseAdapter.BaseListAdapter
import com.tripl3dev.luffyyview.baseAdapter.MainHolderInterface
import com.tripl3dev.prettystates.StatesConstants
import com.tripl3dev.prettystates.setState
import kotlinx.android.synthetic.main.address_details_fragment.*

class AddressDetailsFragment : BaseFragmentWithInjector() {
    var addressDetails: ArrayList<String>? = ArrayList()
    lateinit var mAdapter: BaseListAdapter<String>


    companion object {
        val ADDRESS_DETAILS = "ADDRESS_DETAILS"
        fun instance(address: ArrayList<String>) = AddressDetailsFragment().apply {
            arguments = Bundle().apply {
                putStringArrayList(ADDRESS_DETAILS, address)
            }
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addressDetails = arguments!!.getStringArrayList(ADDRESS_DETAILS)
    }

    override fun getFragmentVM(): Class<out ViewModel> {
        return AddressDetailsViewModel::class.java
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.address_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (addressDetails.isNullOrEmpty()){
            addressesListView.setState(StatesConstants.EMPTY_STATE)
        }else{
            setupList()
        }


    }

    private fun setupList() {
        mAdapter = BaseListAdapter(object : MainHolderInterface<String> {
            override fun getView(type: Int): Int {
                return R.layout.address_details_list_item
            }

            override fun getListCopy(): java.util.ArrayList<String>? {
                return addressDetails
            }
            override fun getViewData(holder: RecyclerView.ViewHolder, t: String, position: Int) {
                val  addressText = holder.itemView.findViewById<TextView>(R.id.addressTV)
                addressText.text = t
            }
        },context!!)
    addressesListView.apply {
        layoutManager = LinearLayoutManager(context!!)
        adapter = mAdapter
        mAdapter.originalList =addressDetails!!
        mAdapter.notifyDataSetChanged()
    }

    }

}
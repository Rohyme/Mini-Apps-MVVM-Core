package com.rohyme.core.presentation.ui.main.fragment

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rohyme.core.R
import com.rohyme.core.presentation.ui.base.BaseFragmentWithInjector

class MainFragment : BaseFragmentWithInjector() {
    override fun getFragmentVM(): Class<out ViewModel> {
        return MainFragmentViewModel::class.java
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment,container,false)
    }
}
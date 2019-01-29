package com.rohyme.core.presentation.ui.main.activity

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import com.rohyme.core.R
import com.rohyme.core.databinding.ActivityMainWithNavBinding
import com.rohyme.core.presentation.appUtils.otherUtils.setContent
import com.rohyme.core.presentation.ui.base.BaseActivityWithInjector

class MainScreenWithNav : BaseActivityWithInjector() {

    val binding: ActivityMainWithNavBinding by setContent<MainScreenWithNav, ActivityMainWithNavBinding>(R.layout.activity_main_with_nav)
    override fun getActivityVM(): Class<out ViewModel> {
        return MainScreenViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding.hello.text = "Say Hello Ya 3m"
        binding.root
//        binding.executePendingBindings()
    }


}

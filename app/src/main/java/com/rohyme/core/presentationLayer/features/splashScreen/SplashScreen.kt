package com.rohyme.core.presentationLayer.features.splashScreen

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.rohyme.core.databinding.ActivitySplashScreenBinding
import com.rohyme.core.shared.di.injectors.FragmentWithInjector


class SplashScreen : FragmentWithInjector<SplashVM>() {
    lateinit var binding: ActivitySplashScreenBinding
    override val viewModelClass: Class<SplashVM> = SplashVM::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ActivitySplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({
            Toast.makeText(context,"Here we are Splash",Toast.LENGTH_SHORT).show()
        }, 5000)
    }


}


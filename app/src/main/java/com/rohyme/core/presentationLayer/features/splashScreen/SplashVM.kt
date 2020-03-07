package com.rohyme.core.presentationLayer.features.splashScreen

import androidx.lifecycle.ViewModel
import javax.inject.Inject
import javax.inject.Singleton

class SplashVM @Inject constructor(here : HereWeAre) : ViewModel() {
val text = here.text
}

@Singleton
class HereWeAre @Inject constructor(){
    val text = "Hell from Other Side"
}
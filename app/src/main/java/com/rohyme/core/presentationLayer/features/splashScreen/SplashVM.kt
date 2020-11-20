package com.rohyme.core.presentationLayer.features.splashScreen

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import javax.inject.Singleton

class SplashVM @ViewModelInject constructor(here : HereWeAre,
@Assisted private val stateHandle: SavedStateHandle ) : ViewModel() {
val text = here.text
}

@Singleton
class HereWeAre @Inject constructor(){
    val text = "Hell from Other Side"
}
package com.rohyme.core.presentationLayer.features.parentActivity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.rohyme.core.R.layout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
    }

}

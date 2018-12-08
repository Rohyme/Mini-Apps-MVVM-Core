package com.rohyme.aro7ezai.presentation.ui.screens.splashScreen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.rohyme.aro7ezai.R
import com.rohyme.aro7ezai.presentation.ui.screens.mainScreen.MainActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            startActivity(Intent(this@SplashScreen , MainActivity::class.java))
            finish()
        },500)
    }
}

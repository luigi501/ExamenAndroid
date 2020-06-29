package com.anzen.android.examenandroid.view

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.anzen.android.examenandroid.R

class SplashActivity : AppCompatActivity() {
    private fun getSplashScreenDuration() = 2000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        waitSplashScreen()
    }

    private fun waitSplashScreen() {
        val splashScreenDuration = getSplashScreenDuration()
        Handler().postDelayed(
            {
                val intent=Intent(baseContext,MainActivity::class.java)
                startActivity(intent)
                finish()
            },
            splashScreenDuration
        )
    }



}

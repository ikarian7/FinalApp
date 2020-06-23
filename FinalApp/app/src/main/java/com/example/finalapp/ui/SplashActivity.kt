package com.example.finalapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.finalapp.R

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long = 500 // 1 sec, was 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

        initViews()
    }

    fun initViews() {
        startTimer()
    }


    fun startTimer() {
        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            startActivity(Intent(this, MainActivity::class.java))

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}
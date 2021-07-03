package com.dicoding.submissionjetpack3.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.submissionjetpack3.R
import com.dicoding.submissionjetpack3.ui.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    companion object {
        private const val TWO_SECOND_IN_MILLIS = 2000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, TWO_SECOND_IN_MILLIS)
    }
}
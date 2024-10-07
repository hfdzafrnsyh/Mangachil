package com.hfdzafrnsyh.mangapid

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.hfdzafrnsyh.mangapid.databinding.ActivitySplashScreenBinding
import com.hfdzafrnsyh.mangapid.ui.MainActivity


@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var activitySplashScreenBinding: ActivitySplashScreenBinding

    companion object {
      const val TIME_LOADING = 2500
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySplashScreenBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(activitySplashScreenBinding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            toMainActivity()
        }, TIME_LOADING.toLong())

    }


    private fun toMainActivity(){
        val intent =  Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
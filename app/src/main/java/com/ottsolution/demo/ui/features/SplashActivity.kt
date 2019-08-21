package com.ottsolution.demo.ui.features

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ottsolution.demo.R
import com.ottsolution.demo.ui.features.home.HomeActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initView()

    }

    private fun initView() {
        try {
            videoView.setMediaController(null)
            videoView.setOnCompletionListener({ onCompletedCheck() })

            val path = "android.resource://" + getPackageName() + "/" + R.raw.splash
            videoView.setVideoURI(Uri.parse(path))
            videoView.start()
        } catch (e: Exception) {
            Log.e("", e.message, e)
        }

    }

    private fun onCompletedCheck() {
        startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
        finish()
    }
}

package com.test.doyouwanttograduate

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class activity_splash: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        startLoading()
    }

    private fun startLoading() {
        val handler = Handler()
        handler.postDelayed(Runnable {
            val intent = Intent(this@activity_splash, activity_home::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }

}

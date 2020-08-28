
package com.test.doyouwanttograduate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class Splash: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        val pref = getSharedPreferences("setting", Context.MODE_PRIVATE)
        val number:Int = pref.getInt("grade", 0)

        if(number!=0){
            startLoading()
        }
        else{
            nchoice_Loading()
        }
    }

    private fun startLoading() {
        val handler = Handler()
        handler.postDelayed(Runnable {
            val intent = Intent(this@Splash, activity_home::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }

    private fun nchoice_Loading(){
        val handler = Handler()
        handler.postDelayed(Runnable {
            val intent = Intent(this@Splash, NumberChoice::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }

}
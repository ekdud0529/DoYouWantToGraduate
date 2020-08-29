
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

        // 선택한 학번값 가져오기
        val pref = getSharedPreferences("setting", Context.MODE_PRIVATE)
        val number:Int = pref.getInt("grade", 0)

        // 학번 선택했으면 바로 홈 화면으로 아니면 학번 선택화면으로 이동
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
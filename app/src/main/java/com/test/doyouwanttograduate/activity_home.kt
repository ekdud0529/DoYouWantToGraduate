package com.test.doyouwanttograduate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home.*

class activity_home : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        val intent_t = Intent(this@activity_home, MainActivity::class.java)
        val intent_g = Intent(this@activity_home, activity_mng::class.java)

        timetable.setOnClickListener{
            startActivity(intent_t)
        }

        grade.setOnClickListener{
            startActivity(intent_g)
        }

    }
}
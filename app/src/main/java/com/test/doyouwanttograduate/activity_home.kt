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

        timetable_bnt.setOnClickListener{
            val intent_t = Intent(this@activity_home, MainActivity::class.java)
            startActivity(intent_t)
        }

        grade_bnt.setOnClickListener{
            val intent_g = Intent(this@activity_home, activity_mng::class.java)
            startActivity(intent_g)
        }

    }
}
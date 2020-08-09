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
        //val intent_g = Intent(this@activity_home, timetable11::class.java)

        timetable.setOnClickListener{
            startActivity(intent_t)
        }

        grade.setOnClickListener{

        }

        home_bnt.setOnClickListener{
            val  intent_hbnt = Intent(this@activity_home, activity_home::class.java)
            startActivity(intent_hbnt)
        }

        timet_bnt.setOnClickListener{
            val  intent_tbnt = Intent(this@activity_home, activity_timetable11::class.java)
            startActivity(intent_tbnt)
        }

        fin_bnt.setOnClickListener{
            val  intent_fbnt = Intent(this@activity_home, activity_mng::class.java)
            startActivity(intent_fbnt)
        }
    }
}
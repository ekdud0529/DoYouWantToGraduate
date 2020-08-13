package com.test.doyouwanttograduate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class activity_timetable11 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timetable11)


        home_bnt.setOnClickListener{
            val  intent_hbnt = Intent(this@activity_timetable11, activity_home::class.java)
            startActivity(intent_hbnt)
        }

        timet_bnt.setOnClickListener{
            val  intent_tbnt = Intent(this@activity_timetable11, activity_timetable11::class.java)
            startActivity(intent_tbnt)
        }

        fin_bnt.setOnClickListener{
            val  intent_fbnt = Intent(this@activity_timetable11, activity_mng::class.java)
            startActivity(intent_fbnt)
        }

    }
}
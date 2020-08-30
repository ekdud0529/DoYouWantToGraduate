package com.test.doyouwanttograduate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.fin_bnt
import kotlinx.android.synthetic.main.activity_main.home_bnt
import kotlinx.android.synthetic.main.timetable11.*

class activity_timetable11 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timetable11)

        subjadd_bnt.setOnClickListener{
            val intent = Intent(this@activity_timetable11, SemesterChoice::class.java)
            startActivity(intent)
        }



        home_bnt.setOnClickListener{
            val  intent_hbnt = Intent(this@activity_timetable11, activity_home::class.java)
            startActivity(intent_hbnt)
        }

        fin_bnt.setOnClickListener{
            val  intent_fbnt = Intent(this@activity_timetable11, activity_mng::class.java)
            startActivity(intent_fbnt)
        }

        set_bnt.setOnClickListener{
            val  intent_tbnt = Intent(this@activity_timetable11, settingActivity::class.java)
            startActivity(intent_tbnt)
        }

    }
}
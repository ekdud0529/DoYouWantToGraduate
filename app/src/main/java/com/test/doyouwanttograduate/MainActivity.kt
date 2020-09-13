package com.test.doyouwanttograduate

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.fin_bnt
import kotlinx.android.synthetic.main.activity_main.home_bnt
import kotlinx.android.synthetic.main.activity_main.set_bnt
import kotlinx.android.synthetic.main.setting.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val grade = resources.getStringArray(R.array.grade)
        val semester = resources.getStringArray(R.array.semester)

        val adapter1 = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, grade)
        val adapter2 = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, semester)

        grade_selector.adapter = adapter1
        semester_selector.adapter = adapter2

        complete.setOnClickListener{
            val intent11 = Intent(this@MainActivity, activity_timetable11::class.java)
            //    ...


            if(grade_selector.selectedItem == "1학년") {
                if(semester_selector.selectedItem == "1학기") {
                    startActivity(intent11)
                }
            }

        }

        home_bnt.setOnClickListener{
            val  intent_hbnt = Intent(this@MainActivity, activity_home::class.java)
            startActivity(intent_hbnt)
        }

        fin_bnt.setOnClickListener{
            val  intent_fbnt = Intent(this@MainActivity, activity_mng::class.java)
            startActivity(intent_fbnt)
        }

        set_bnt.setOnClickListener{
            val  intent_tbnt = Intent(this@MainActivity, activity_setting::class.java)
            startActivity(intent_tbnt)
            overridePendingTransition(0, 0)
        }

    }
}
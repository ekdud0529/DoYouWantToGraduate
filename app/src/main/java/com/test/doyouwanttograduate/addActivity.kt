package com.test.doyouwanttograduate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.fin_bnt
import kotlinx.android.synthetic.main.activity_main.home_bnt
import kotlinx.android.synthetic.main.activity_main.set_bnt
import kotlinx.android.synthetic.main.add_subject.*
import kotlinx.android.synthetic.main.timetable.*

class addActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_subject)


//        val name = name_edt.text.toString().trim()
//        val bsm = bsm_edt.text.toString().trim()
//        val plan = plan_edt.text.toString().trim()
//        val num = num_edt.text.toString().trim()
//        val state = state_edt.text.toString().trim()
//


//        add_complete.setOnClickListener {
//            val intent_add = Intent(this@addActivity, activity_timetable::class.java)
//
//
//        }









        home_bnt.setOnClickListener {
            val intent_hbnt = Intent(this@addActivity, activity_home::class.java)
            startActivity(intent_hbnt)
        }

        set_bnt.setOnClickListener {
            val intent_tbnt = Intent(this@addActivity, activity_setting::class.java)
            startActivity(intent_tbnt)
            overridePendingTransition(0, 0)
        }

        fin_bnt.setOnClickListener {
            val intent_fbnt = Intent(this@addActivity, activity_mng::class.java)
            startActivity(intent_fbnt)
        }
    }


}
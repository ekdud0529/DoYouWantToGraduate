package com.test.doyouwanttograduate

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.fin_bnt
import kotlinx.android.synthetic.main.activity_main.home_bnt
import kotlinx.android.synthetic.main.activity_main.set_bnt
import kotlinx.android.synthetic.main.add_subject.*
import kotlinx.android.synthetic.main.setting.*
import kotlinx.android.synthetic.main.timetable11.*

class addActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_subject)


        val name = name_edt.text.toString().trim()
        val bsm = bsm_edt.text.toString().trim()
        val plan = plan_edt.text.toString().trim()
        val num = num_edt.text.toString().trim()
        val state = state_edt.text.toString().trim()
        val grade = ""
        val semester = ""
        val is_checked = true



        add_complete.setOnClickListener {
            val intent = Intent(this, activity_timetable11::class.java)

            if(name.isEmpty()){
                name_edt.error = "Please insert name"
                return@setOnClickListener
            }
            if(bsm.isEmpty()){
                bsm_edt.error = "Please insert bsm"
                return@setOnClickListener
            }
            if(plan.isEmpty()){
                plan_edt.error = "Please insert plan"
                return@setOnClickListener
            }
            if(num.isEmpty()){
                num_edt.error = "Please insert num"
                return@setOnClickListener
            }
            if(state.isEmpty()){
                state_edt.error = "Please insert state"
                return@setOnClickListener
            }



            // 적은 정보 가지고 해당 학년 학기 timetable에 넣기
            val sharedPreferences = getSharedPreferences("edit_setting", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()

            editor.putString("name", name)
            editor.putString("bsm", bsm)
            editor.putString("plan", plan)
            editor.putString("num", num)
            editor.putString("state", state)



            startActivity(intent)
        }









        home_bnt.setOnClickListener{
            val  intent_hbnt = Intent(this@addActivity, activity_home::class.java)
            startActivity(intent_hbnt)
        }

        set_bnt.setOnClickListener{
            val  intent_tbnt = Intent(this@addActivity, activity_setting::class.java)
            startActivity(intent_tbnt)
            overridePendingTransition(0, 0)
        }

        fin_bnt.setOnClickListener{
            val  intent_fbnt = Intent(this@addActivity, activity_mng::class.java)
            startActivity(intent_fbnt)
        }
    }


}
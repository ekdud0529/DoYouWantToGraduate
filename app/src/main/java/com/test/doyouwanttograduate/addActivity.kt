package com.test.doyouwanttograduate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.fin_bnt
import kotlinx.android.synthetic.main.activity_main.home_bnt
import kotlinx.android.synthetic.main.activity_main.timet_bnt
import kotlinx.android.synthetic.main.add_subject.*
import kotlinx.android.synthetic.main.timetable11.*

class addActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_subject)

        // edittext에 추가할 과목 받아서 db에 저장, 불러오기 시도..try..
        name_edt.setOnClickListener() {
            val name = name_edt.text.toString().trim()
            val bsm = bsm_edt.text.toString().trim()
            val plan = plan_edt.text.toString().trim()
            val num = num_edt.text.toString().trim()
            val state = state_edt.text.toString().trim()


            if(name.isEmpty()){
                name_edt.error = "Please insert name"
                return@setOnClickListener
            }

            if(bsm.isEmpty()){
                bsm_edt.error = "Please insert bsm"
            }

            if(plan.isEmpty()){
                plan_edt.error = "Please insert plan"
            }

            if(num.isEmpty()){
                num_edt.error = "Please insert num"
            }

            if(state.isEmpty()){
                state_edt.error = "Please insert state"
            }








            add_complete.setOnClickListener {
                val intent = Intent(this, activity_timetable11::class.java)
                             // 여기서도 직전에 넘어온 timetable의 학년학기 정보를 받아서 거기로 돌아가야함!
                startActivity(intent)
            }
        }









        home_bnt.setOnClickListener{
            val  intent_hbnt = Intent(this@addActivity, activity_home::class.java)
            startActivity(intent_hbnt)
        }

        timet_bnt.setOnClickListener{
            val  intent_tbnt = Intent(this@addActivity, MainActivity::class.java)
            startActivity(intent_tbnt)
        }

        set_bnt.setOnClickListener{
            val  intent_fbnt = Intent(this@addActivity, activity_mng::class.java)
            startActivity(intent_fbnt)
        }
    }


}
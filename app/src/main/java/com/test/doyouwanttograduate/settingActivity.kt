package com.test.doyouwanttograduate

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.setting.*
import kotlinx.android.synthetic.main.setting.fin_bnt
import kotlinx.android.synthetic.main.setting.home_bnt
import kotlinx.android.synthetic.main.setting.set_bnt

class settingActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting)

        val num = resources.getStringArray(R.array.num_change)

        val adapter = ArrayAdapter(this@settingActivity, android.R.layout.simple_spinner_dropdown_item, num)

        num_selector.adapter = adapter

        select.setOnClickListener {
            //Todo : 스피너 선택한걸로 학번 바꾸기
        }



        home_bnt.setOnClickListener{
            val  intent_hbnt = Intent(this@settingActivity, activity_home::class.java)
            startActivity(intent_hbnt)
        }

        fin_bnt.setOnClickListener{
            val  intent_fbnt = Intent(this@settingActivity, activity_mng::class.java)
            startActivity(intent_fbnt)
        }

        set_bnt.setOnClickListener{
            val  intent_tbnt = Intent(this@settingActivity, settingActivity::class.java)
            startActivity(intent_tbnt)
        }

    }
}
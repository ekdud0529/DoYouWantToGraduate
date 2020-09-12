package com.test.doyouwanttograduate

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.setting.*
import kotlinx.android.synthetic.main.setting.fin_bnt
import kotlinx.android.synthetic.main.setting.home_bnt
import kotlinx.android.synthetic.main.setting.set_bnt

class activity_setting : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting)

        // Spinner
        val num = resources.getStringArray(R.array.num_change)
        val adapter = ArrayAdapter(this@activity_setting, android.R.layout.simple_spinner_dropdown_item, num)
        num_selector.adapter = adapter

        // 학번 변경
        val sharedPreferences = getSharedPreferences("setting", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        select.setOnClickListener {
            var t1 = Toast.makeText(this, "학번을 선택해주세요", Toast.LENGTH_SHORT)

            when(num_selector.selectedItem){
                "14 / 15 / 16 학번"
                -> editor.putInt("grade", 14)
                "17 / 18 학번" -> editor.putInt("grade", 17)
                "19 학번" -> editor.putInt("grade", 19)
                "20 학번" -> editor.putInt("grade", 20)
                else -> t1.show()
            }

            if(editor.commit()){
                    var complete = Toast.makeText(this, "학번이 변경되었습니다.", Toast.LENGTH_SHORT)
                    complete.show()
            }

        }

        home_bnt.setOnClickListener{
            val  intent_hbnt = Intent(this@activity_setting, activity_home::class.java)
            startActivity(intent_hbnt)
            overridePendingTransition(0, 0)
        }

        fin_bnt.setOnClickListener{
            val  intent_fbnt = Intent(this@activity_setting, activity_mng::class.java)
            startActivity(intent_fbnt)
            overridePendingTransition(0, 0)
        }

        set_bnt.setOnClickListener{
            val  intent_tbnt = Intent(this@activity_setting, activity_setting::class.java)
            startActivity(intent_tbnt)
            overridePendingTransition(0, 0)
        }

    }

}
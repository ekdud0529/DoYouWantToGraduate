package com.test.doyouwanttograduate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.credit_management.*

class activity_mng : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.credit_management)

        val pref = getSharedPreferences("setting", Context.MODE_PRIVATE)
        val number:Int = pref.getInt("grade", 0)


        home_bnt.setOnClickListener{
            val  intent_hbnt = Intent(this@activity_mng, activity_home::class.java)
            startActivity(intent_hbnt)
        }

        fin_bnt.setOnClickListener{
            val  intent_fbnt = Intent(this@activity_mng, activity_mng::class.java)
            startActivity(intent_fbnt)
        }

        set_bnt.setOnClickListener{
            val  intent_setbnt = Intent(this@activity_mng, activity_setting::class.java)
            startActivity(intent_setbnt)
        }

    }
}
package com.test.doyouwanttograduate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_number_choice.*

class NumberChoice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_choice)

        number_complete.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)



        }



        home_bnt.setOnClickListener{
            val  intent_hbnt = Intent(this@NumberChoice, activity_home::class.java)
            startActivity(intent_hbnt)
        }

        timet_bnt.setOnClickListener{
            val  intent_tbnt = Intent(this@NumberChoice, MainActivity::class.java)
            startActivity(intent_tbnt)
        }

        fin_bnt.setOnClickListener{
            val  intent_fbnt = Intent(this@NumberChoice, activity_mng::class.java)
            startActivity(intent_fbnt)
        }
    }
}
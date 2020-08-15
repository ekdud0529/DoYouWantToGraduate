package com.test.doyouwanttograduate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.fin_bnt
import kotlinx.android.synthetic.main.activity_main.home_bnt
import kotlinx.android.synthetic.main.activity_main.timet_bnt
import kotlinx.android.synthetic.main.activity_semester_choice.*

class SemesterChoice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_semester_choice)

        one_one_button.setOnClickListener{
            val intentoneone = Intent(this@SemesterChoice, SubjectList::class.java)
            startActivity(intentoneone)
        }



        home_bnt.setOnClickListener{
            val  intent_hbnt = Intent(this@SemesterChoice, activity_home::class.java)
            startActivity(intent_hbnt)
        }

        timet_bnt.setOnClickListener{
            val  intent_tbnt = Intent(this@SemesterChoice, MainActivity::class.java)
            startActivity(intent_tbnt)
        }

        fin_bnt.setOnClickListener{
            val  intent_fbnt = Intent(this@SemesterChoice, activity_mng::class.java)
            startActivity(intent_fbnt)
        }
    }
}
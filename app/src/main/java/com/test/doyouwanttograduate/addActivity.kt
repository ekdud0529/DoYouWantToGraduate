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
    private var grade = listOf<Db>()
    private var sem = listOf<semDb>()
    private var dbDb : AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_subject)


        val name = name_edt.text.toString().trim()
        val bsm = bsm_edt.text.toString().trim()
        val plan = plan_edt.text.toString().trim()
        val num = num_edt.text.toString().trim()
        val state = state_edt.text.toString().trim()


        /** table 학년학기 db가져오기 **/

       dbDb = AppDatabase.getInstance(this)

        val addRunnable = Runnable {
            val newDb = Db()
            val new_semDb = semDb()
            newDb.grade = grade_sel.selectedItem.toString()
            new_semDb.semester = semester_sel.selectedItem.toString()
            dbDb?.dbDao()?.insert(newDb)
            dbDb?.dbDao()?.insert_sem(new_semDb)
        }

        val addThread = Thread(addRunnable)
        addThread.start()
        finish()

        /** ------------------------ **/


        add_complete.setOnClickListener {
            val intent_add = Intent(this@addActivity, activity_timetable11::class.java)


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
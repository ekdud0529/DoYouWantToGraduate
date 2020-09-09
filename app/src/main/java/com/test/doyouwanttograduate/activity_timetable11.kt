package com.test.doyouwanttograduate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.fin_bnt
import kotlinx.android.synthetic.main.activity_main.home_bnt
import kotlinx.android.synthetic.main.home.*
import kotlinx.android.synthetic.main.timetable11.*

class activity_timetable11 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timetable11)

        //과목 선택해서 저장한 리스트 정보 받아와 여기 timetable의 리스트뷰에 뿌려야됨 !

        subject_add.setOnClickListener {
            val intent = Intent(this@activity_timetable11, SubjectListActivity::class.java)
            startActivity(intent)
        }

      /*



        etc_add.setOnClickListener{
            val intent_etc = Intent(this@activity_timetable11, addActivity::class.java)
            startActivity(intent_etc)
        }

        subject_add.setOnClickListener{
            val intent_subadd = Intent(this@activity_timetable11, SubjectListActivity::class.java)
            startActivity(intent_subadd)
        }

        delete_bt.setOnClickListener{
            // timetable내용 지우기 ㄱㄱ
        }

*/

        home_bnt.setOnClickListener{
            val  intent_hbnt = Intent(this@activity_timetable11, activity_home::class.java)
            startActivity(intent_hbnt)
            overridePendingTransition(0, 0)
        }

        set_bnt.setOnClickListener{
            val  intent_tbnt = Intent(this@activity_timetable11, activity_setting::class.java)
            startActivity(intent_tbnt)
            overridePendingTransition(0, 0)
        }

        fin_bnt.setOnClickListener{
            val  intent_fbnt = Intent(this@activity_timetable11, activity_mng::class.java)
            startActivity(intent_fbnt)
            overridePendingTransition(0, 0)
        }

    }
}
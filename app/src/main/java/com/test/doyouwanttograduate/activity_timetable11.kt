package com.test.doyouwanttograduate

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.fin_bnt
import kotlinx.android.synthetic.main.activity_main.home_bnt
import kotlinx.android.synthetic.main.timetable11.*
import kotlinx.android.synthetic.main.timetable11.timet_bnt


class activity_timetable11 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timetable11)

        //과목 선택해서 저장한 리스트 정보 받아와 여기 timetable의 리스트뷰에 뿌려야됨 !

        val gradeEtc = resources.getStringArray(R.array.grade)
        val semester = resources.getStringArray(R.array.semester)

        val adapt1 = ArrayAdapter<String>(this@activity_timetable11, android.R.layout.simple_list_item_1, gradeEtc)
        val adapt2 = ArrayAdapter<String>(this@activity_timetable11, android.R.layout.simple_list_item_1, semester)

        grade_sel.adapter = adapt1
        semester_sel.adapter = adapt2





        etc_add.setOnClickListener{
            val intent_etc = Intent(this@activity_timetable11, addActivity::class.java)
            startActivity(intent_etc)
        }


        subject_add.setOnClickListener{
            val intent_subadd = Intent(this@activity_timetable11, SubjectListActivity::class.java)
            startActivity(intent_subadd)

            val intent = Intent(applicationContext, MainListAdapter::class.java)

            if(grade_sel.selectedItem == "1학년") {
                if(semester_sel.selectedItem == "1학기") {
                    intent.putExtra("grade", "1학년")
                    intent.putExtra("semester", "1학기")
                    startActivity(intent)
                }
            }
            if(grade_sel.selectedItem == "1학년") {
                if(semester_sel.selectedItem == "2학기") {
                    intent.putExtra("grade", "1학년")
                    intent.putExtra("semester", "2학기")
                    startActivity(intent)
                }
            }
            if(grade_sel.selectedItem == "2학년") {
                if(semester_sel.selectedItem == "1학기") {
                    intent.putExtra("grade", "2학년")
                    intent.putExtra("semester", "1학기")
                    startActivity(intent)
                }
            }
            if(grade_sel.selectedItem == "2학년") {
                if(semester_sel.selectedItem == "2학기") {
                    intent.putExtra("grade", "2학년")
                    intent.putExtra("semester", "2학기")
                    startActivity(intent)
                }
            }
            if(grade_sel.selectedItem == "3학년") {
                if(semester_sel.selectedItem == "1학기") {
                    intent.putExtra("grade", "3학년")
                    intent.putExtra("semester", "1학기")
                    startActivity(intent)
                }
            }
            if(grade_sel.selectedItem == "3학년") {
                if(semester_sel.selectedItem == "2학기") {
                    intent.putExtra("grade", "3학년")
                    intent.putExtra("semester", "2학기")
                    startActivity(intent)
                }
            }
            if(grade_sel.selectedItem == "4학년") {
                if(semester_sel.selectedItem == "1학기") {
                    intent.putExtra("grade", "4학년")
                    intent.putExtra("semester", "1학기")
                    startActivity(intent)
                }
            }
            if(grade_sel.selectedItem == "4학년") {
                if(semester_sel.selectedItem == "2학기") {
                    intent.putExtra("grade", "4학년")
                    intent.putExtra("semester", "2학기")
                    startActivity(intent)
                }
            }


        }

        delete_bt.setOnClickListener{
            // timetable내용 지우기 ㄱㄱ
        }





        home_bnt.setOnClickListener{
            val  intent_hbnt = Intent(this@activity_timetable11, activity_home::class.java)
            startActivity(intent_hbnt)
            overridePendingTransition(0, 0)
        }

        timet_bnt.setOnClickListener{
            val  intent_tbnt = Intent(this@activity_timetable11, MainActivity::class.java)
            startActivity(intent_tbnt)
        }

        fin_bnt.setOnClickListener{
            val  intent_fbnt = Intent(this@activity_timetable11, activity_mng::class.java)
            startActivity(intent_fbnt)
            overridePendingTransition(0, 0)
        }

    }
}
package com.test.doyouwanttograduate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_subject_list.*
import kotlinx.android.synthetic.main.activity_subject_list.fin_bnt
import kotlinx.android.synthetic.main.activity_subject_list.home_bnt
import kotlinx.android.synthetic.main.activity_subject_list.timet_bnt
import javax.security.auth.Subject


class SubjectListActivity : AppCompatActivity() {

    var subjectList = arrayListOf<com.test.doyouwanttograduate.Subject>(

        Subject("데이터 베이스", "o","3","3"),
        Subject("모바일 프로그래밍", "x","3","3"),
        Subject("프로그래밍 언어론", "x","3","3"),
        Subject("네트워크", "o","3","3"),
        Subject("객체 지향 프로그래밍" , "o","3","3"),
        Subject("수학_1", "x","3","3"),
        Subject("수치해석", "x","3","3"),
        Subject("컴퓨터 구조", "o","3","3")

    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject_list)


        val listadapter = MainListAdapter(this, subjectList)
        mainListView.adapter = listadapter

        val grade = resources.getStringArray(R.array.grade)
        val semester = resources.getStringArray(R.array.semester)

        val adapt1 = ArrayAdapter<String>(this@SubjectListActivity, android.R.layout.simple_list_item_1, grade)
        val adapt2 = ArrayAdapter<String>(this@SubjectListActivity, android.R.layout.simple_list_item_1, semester)

        grade_sel.adapter = adapt1
        semester_sel.adapter = adapt2

        //스피너 이벤트 구간


        home_bnt.setOnClickListener{
            val  intent_hbnt = Intent(this@SubjectListActivity, activity_home::class.java)
            startActivity(intent_hbnt)
        }

        timet_bnt.setOnClickListener{
            val  intent_tbnt = Intent(this@SubjectListActivity, MainActivity::class.java)
            startActivity(intent_tbnt)
        }

        fin_bnt.setOnClickListener{
            val  intent_fbnt = Intent(this@SubjectListActivity, activity_mng::class.java)
            startActivity(intent_fbnt)
        }

    }
}
package com.test.doyouwanttograduate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_subject_list.*
import kotlinx.android.synthetic.main.activity_subject_list.fin_bnt
import kotlinx.android.synthetic.main.activity_subject_list.home_bnt
import kotlinx.android.synthetic.main.activity_subject_list.timet_bnt
import javax.security.auth.Subject


class SubjectList : AppCompatActivity() {

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



        home_bnt.setOnClickListener{
            val  intent_hbnt = Intent(this@SubjectList, activity_home::class.java)
            startActivity(intent_hbnt)
        }

        timet_bnt.setOnClickListener{
            val  intent_tbnt = Intent(this@SubjectList, MainActivity::class.java)
            startActivity(intent_tbnt)
        }

        fin_bnt.setOnClickListener{
            val  intent_fbnt = Intent(this@SubjectList, activity_mng::class.java)
            startActivity(intent_fbnt)
        }

    }
}
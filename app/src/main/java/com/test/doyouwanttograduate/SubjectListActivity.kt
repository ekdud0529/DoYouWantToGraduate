package com.test.doyouwanttograduate

import android.content.Intent
import android.graphics.Insets.add
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.widget.AbsListView.CHOICE_MODE_MULTIPLE
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OneShotPreDrawListener.add
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_subject_list.*
import kotlinx.android.synthetic.main.activity_subject_list.fin_bnt
import kotlinx.android.synthetic.main.activity_subject_list.home_bnt
import kotlinx.android.synthetic.main.activity_subject_list.timet_bnt
import android.widget.ListView.CHOICE_MODE_MULTIPLE as CHOICE_MODE_MULTIPLE1


class SubjectListActivity : AppCompatActivity() {

    var subjectList_11 = arrayListOf<com.test.doyouwanttograduate.Subject>(

        Subject("수학1", "o","0","3","교양"),
        Subject("일반물리학1", "o","0","3","교양"),
        Subject("일반물리학실험1", "o","0","1","교양"),
        Subject("실용영어", "x","0","3","교양"),
        Subject("프리젠테이션기법의이해" , "x","0","3","교양"),
        Subject("철학의이해", "x","0","3","교양"),
        Subject("컴퓨터프로그래밍의기초", "x","0","3","교양")

    )

    var subjectList_12 = arrayListOf<com.test.doyouwanttograduate.Subject>(

        Subject("수학2", "o","0","3","교양"),
        Subject("영어회화", "x","0","3","교양"),
        Subject("글쓰기", "x","0","3","교양"),
        Subject("경영과창업의이해", "x","0","3","교양"),
        Subject("c언어기초" , "x","0","3","교양"),
        Subject("디지털논리설계(~18)", "x","0","3","교양"),
        Subject("디지털논리설계(19~)", "x","0","3","전선")

    )

    private var mDatabase: FirebaseDatabase? = null
    private var mReference: DatabaseReference? = null
    private var mChild: ChildEventListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject_list)



        val gradeEtc = resources.getStringArray(R.array.gradeEtc)
        val semester = resources.getStringArray(R.array.semester)

        val adapt1 = ArrayAdapter<String>(this@SubjectListActivity, android.R.layout.simple_list_item_1, gradeEtc)
        val adapt2 = ArrayAdapter<String>(this@SubjectListActivity, android.R.layout.simple_list_item_1, semester)

        grade_sel.adapter = adapt1
        semester_sel.adapter = adapt2

        //스피너 이벤트 구간





        val listAdapter = MainListAdapter(this, subjectList_11)
        mainListView.adapter = listAdapter

        g_s_complete.setOnClickListener(){
            if(grade_sel.selectedItem == "1학년") {
                if(semester_sel.selectedItem == "1학기") {
                    val listAdapter = MainListAdapter(this, subjectList_11)
                    mainListView.adapter = listAdapter
                }
            }
            if(grade_sel.selectedItem == "1학년") {
                if(semester_sel.selectedItem == "2학기") {
                    val listAdapter = MainListAdapter(this, subjectList_12)
                    mainListView.adapter = listAdapter
                }
            }
        }







        home_bnt.setOnClickListener {
            val intent_hbnt = Intent(this@SubjectListActivity, activity_home::class.java)
            startActivity(intent_hbnt)
        }

        timet_bnt.setOnClickListener {
            val intent_tbnt = Intent(this@SubjectListActivity, MainActivity::class.java)
            startActivity(intent_tbnt)
        }

        fin_bnt.setOnClickListener {
            val intent_fbnt = Intent(this@SubjectListActivity, activity_mng::class.java)
            startActivity(intent_fbnt)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mReference!!.removeEventListener(mChild!!)
    }
}
package com.test.doyouwanttograduate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.fin_bnt
import kotlinx.android.synthetic.main.activity_main.home_bnt
import kotlinx.android.synthetic.main.activity_subject_list.*
import kotlinx.android.synthetic.main.timetable11.*
import kotlinx.android.synthetic.main.timetable11.g_s_complete
import kotlinx.android.synthetic.main.timetable11.grade_sel
import kotlinx.android.synthetic.main.timetable11.semester_sel
import kotlinx.android.synthetic.main.timetable11.set_bnt


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





        //기타 과목 입력 받는 화면으로 이동
        etc_add.setOnClickListener{
            val intent_etc = Intent(this@activity_timetable11, addActivity::class.java)
            startActivity(intent_etc)
        }




        // 스피너 저장 확인 버튼 처리
        g_s_complete.setOnClickListener{

            /* SubjectListActivity에 timetable의 학년과 학기 정보 넘겨주자
            val intent = Intent(applicationContext, SubjectListActivity::class.java)
            intent.putExtra("grade", "grade")
            intent.putExtra("semester", semester_sel.selectedItem)
            */









            /*** subjectListActivity에서 받아온 체크된 리스트 불러와서 timetable에 뿌려주기 ***/

            //Json 으로 만들기 위한 Gson
            var makeGson = GsonBuilder().create()

            // 저장 타입 지정
            var listType : TypeToken<MutableList<Subject>> = object : TypeToken<MutableList<Subject>>() {}

            // 데이터를 Json 형태로 변환
            var sp = getSharedPreferences("list_setting", Context.MODE_PRIVATE)
            var strContact = sp.getString("checked_list", "")

            // 변환
            val datas : ArrayList<Subject> = makeGson.fromJson(strContact,listType.type)


            val listAdapter = MainListAdapter(this, datas)
            tableListView.adapter = listAdapter

        }





        subject_add.setOnClickListener{
            val intent_subadd = Intent(this@activity_timetable11, SubjectListActivity::class.java)
            startActivity(intent_subadd)
        }


        delete_bt.setOnClickListener{
            // timetable내용 지우기 ㄱㄱ
        }





        home_bnt.setOnClickListener{
            val  intent_hbnt = Intent(this@activity_timetable11, activity_home::class.java)
            startActivity(intent_hbnt)
            overridePendingTransition(0, 0)
        }

        set_bnt.setOnClickListener{
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
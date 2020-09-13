package com.test.doyouwanttograduate

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
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


@Suppress("UNCHECKED_CAST")
class activity_timetable11 : AppCompatActivity() {
    @SuppressLint("WrongConstant")
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

        //tableListView에 띄울 list
        val table_items: MutableList<Subject> = mutableListOf()




        // 스피너 저장 확인 버튼 처리
        g_s_complete.setOnClickListener{

            val listAdapter = MainListAdapter(this, table_items as ArrayList<Subject>)
            tableListView.adapter = listAdapter

            /*** 0. 선택한 grade와 semester db에 저장 ***/
            val sharedPreferences = getSharedPreferences("table_setting", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()

            editor.putStringSet("grade", grade_sel.selectedItem as MutableSet<String>?)
            editor.putStringSet("semester", semester_sel.selectedItem as MutableSet<String>?)


            editor.apply()
            Log.e("tag","시간표 정보 저장 완료")


            //기타 과목 입력 받는 화면으로 이동
            etc_add.setOnClickListener{
                val intent_etc = Intent(this@activity_timetable11, addActivity::class.java)
                startActivity(intent_etc)
            }


            /*** 1. edittext에서 받아온 체크된 리스트 불러와서 timetable에 뿌려주기 ***/


            //edittext에서 저장한 db불러오기
            val pref = getSharedPreferences("edit_setting", Context.MODE_PRIVATE)
            val name = pref.getString("name", "")
            val bsm = pref.getString("bsm", "")
            val plan = pref.getString("plan", "")
            val num = pref.getString("num", "")
            val state = pref.getString("state", "")
            val grade = pref.getString("grade","")
            val sem = pref.getString("semester","")


            table_items.add(Subject(name.toString(), bsm.toString(), plan.toString(), num.toString(), state.toString(), true, grade.toString(), sem.toString()))




            /*** 2. subjectListActivity에서 받아온 체크된 리스트 불러와서 timetable에 뿌려주기
             *
             * //여기서 json으로 리스트 가져오기 실패하면 그냥 각각 재료로 가져오자. 그게 add하기 나을수도 ?
             *
             *
             * ***/

            //Json 으로 만들기 위한 Gson
            var makeGson = GsonBuilder().create()

            // 저장 타입 지정
            var listType : TypeToken<MutableList<Subject>> = object : TypeToken<MutableList<Subject>>() {}

            // 데이터를 Json 형태로 변환
            var sp = getSharedPreferences("list_setting", Context.MODE_PRIVATE)
            var strContact = sp.getString("checked_list", "")

            // 변환
            val datas : ArrayList<Subject> = makeGson.fromJson(strContact,listType.type)

            //여기서 합쳐줘야 하는데 아직 방법 모루겠음


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
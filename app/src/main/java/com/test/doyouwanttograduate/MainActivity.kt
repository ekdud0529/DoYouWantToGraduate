package com.test.doyouwanttograduate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.fin_bnt
import kotlinx.android.synthetic.main.activity_main.home_bnt
import kotlinx.android.synthetic.main.activity_main.set_bnt

class MainActivity : AppCompatActivity() {
    //TODO: 본 Activity 는 없는것이 사용감이 좋을거 같아요!

    var selectedGrade: Int = 0
    var selectedSemester: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val grade = resources.getStringArray(R.array.grade)
        val semester = resources.getStringArray(R.array.semester)

        val adapter1 =
            ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, grade)
        val adapter2 =
            ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, semester)

        //Allocate the adapters.
        grade_selector.adapter = adapter1
        semester_selector.adapter = adapter2


        // 스피너에 선택 리스너.
        grade_selector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                selectedGrade = 0
                Toast.makeText(applicationContext, "학년은 필수 선택입니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedGrade = p2 // 선택된 학년.

            }
        }
        semester_selector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                selectedSemester = 0
                Toast.makeText(applicationContext, "학기는 필수 선택입니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedSemester = p2 // 선택된 학기.

            }
        }

        complete.setOnClickListener {
            val intent = Intent(this@MainActivity, activity_timetable::class.java)
            if (selectedGrade > 0 && selectedSemester > 0) {
                intent.putExtra("selected_grade", selectedGrade)
                intent.putExtra("selected_semester", selectedSemester)
                Log.d("LOG<selected>", "selected : {$selectedGrade,$selectedSemester}")
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "학년, 학기를 선택해주세요.", Toast.LENGTH_SHORT).show()
            }
        }


        // TODO: 사실 아래 부분을 구현 하려면 fragment 가 좋을거 같습니다만..  시간적 관계로 유지하고 검토 하겠습니다.
        home_bnt.setOnClickListener {
            val intent_hbnt = Intent(this@MainActivity, activity_home::class.java)
            startActivity(intent_hbnt)
        }

        fin_bnt.setOnClickListener {
            val intent_fbnt = Intent(this@MainActivity, activity_mng::class.java)
            startActivity(intent_fbnt)
        }

        set_bnt.setOnClickListener {
            val intent_tbnt = Intent(this@MainActivity, activity_setting::class.java)
            startActivity(intent_tbnt)
            overridePendingTransition(0, 0)
        }

    }
}
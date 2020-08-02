package com.test.doyouwanttograduate

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val grade = resources.getStringArray(R.array.grade)
        val semester = resources.getStringArray(R.array.semester)

        val adapter1 = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, grade)
        val adapter2 = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, semester)

        grade_selector.adapter = adapter1
        semester_selector.adapter = adapter2

        grade_selector.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                println("선택하세요")
            }

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, pos: Int, id: Long) {

            }
        }

        semester_selector.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                println("선택하세요")
            }

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, pos: Int, id: Long) {

            }
        }

        complete.setOnClickListener{
            val intent11 = Intent(this@MainActivity, timetable11::class.java)
            //    ...


            if(grade_selector.selectedItem == "1학년") {
                if(semester_selector.selectedItem == "1학기") {
                    startActivity(intent11)
                }
            }

        }

    }
}
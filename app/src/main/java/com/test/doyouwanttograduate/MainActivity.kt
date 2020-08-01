package com.test.doyouwanttograduate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val grade = resources.getStringArray(R.array.grade)
        val semester = resources.getStringArray(R.array.semester)

        val adapter1 = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, grade)
        val adapter2 = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_1, grade)

        grade_selector.adapter = adapter1
        semester_selector.adapter = adapter2

        val complete = findViewById<Button>(R.id.complete)
        complete.setOnClickListener{ view ->
            val intent = Intent(this@MainActivity, timetable11Activity::class.java)
            startActivity(intent)
        }
    }
}
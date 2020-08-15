package com.test.doyouwanttograduate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_subject_list.*
import javax.security.auth.Subject


class SubjectList : AppCompatActivity() {

    var subjectList = arrayListOf<com.test.doyouwanttograduate.Subject>(

        Subject("database", "o","3","3"),
        Subject("mobile", "x","3","3"),
        Subject("programming", "x","3","3"),
        Subject("network", "o","3","3")

    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject_list)


        val Adapter = MainListAdapter(this, subjectList)
        mainListView.adapter = Adapter
    }
}
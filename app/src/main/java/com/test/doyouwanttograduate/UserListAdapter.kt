package com.test.doyouwanttograduate

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_subject_list.*


class UserListAdapter(

    val context: Context,
    private val classes: List<UserClass>

) : BaseAdapter() {


    private val checkList: ArrayList<Boolean> =
        ArrayList(BooleanArray(classes.size).toMutableList())


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.list_item, null) // inflater 가 getView 에 있으면 성능이 많이 하락합니다.

        val name = view.findViewById<TextView>(R.id.subjectNameTv)
        val bsm = view.findViewById<TextView>(R.id.subjectBsmTv)
        val plan = view.findViewById<TextView>(R.id.subjectPlanTv)
        val num = view.findViewById<TextView>(R.id.subjectNumTv)
        val state = view.findViewById<TextView>(R.id.subjectStateTv)
        val check = view.findViewById<ImageButton>(R.id.checkbt)

        name.text = classes[position].name
        bsm.text = classes[position].bsm
        plan.text = classes[position].plan
        num.text = classes[position].num.toString()
        state.text = classes[position].state

        //check 박스 상태 표시. (스크롤시 화면 보이기(처리 없을시 아래 리스너에서 바꿔도 원래 대로 돌아와요.))
        if (checkList[position]) {
            check.setImageResource(R.drawable.selected_btn)
        } else {
            check.setImageResource(R.drawable.listrec)
        }


        check.setOnClickListener {
            if (checkList[position]) {
                check.setImageResource(R.drawable.listrec)
            } else {
                check.setImageResource(R.drawable.selected_btn)
            }
            checkList[position] = !checkList[position]
        }
        return view
    }

    override fun getItem(position: Int): Any {
        return checkList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return checkList.size
    }

    fun getCheckedClasses(): List<UserClass> {
        val tempClasses = ArrayList<UserClass>()

        for ((idx, mClass) in classes.withIndex()) {
            if (checkList[idx]) {
                tempClasses.add(mClass)
            }
        }

        return tempClasses
    }
}


package com.test.doyouwanttograduate

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView


class MainListAdapter(
    val context: Context, val grade: ArrayList<Subject>
) : BaseAdapter() {
    private var subjectList: ArrayList<Subject>? = null


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item, null) // inflater 가 getView 에 있으면 성능이 많이 하락합니다.

        val name = view.findViewById<TextView>(R.id.subjectNameTv)
        val bsm = view.findViewById<TextView>(R.id.subjectBsmTv)
        val plan = view.findViewById<TextView>(R.id.subjectPlanTv)
        val num = view.findViewById<TextView>(R.id.subjectNumTv)
        val state = view.findViewById<TextView>(R.id.subjectStateTv)
        val check = view.findViewById<Button>(R.id.checkbt)

        val subject = subjectList!![position]

        name.text = subject.name
        bsm.text = subject.bsm
        plan.text = subject.plan
        num.text = subject.num
        state.text = subject.state

        //check 박스 상태 표시. (스크롤시 화면 보이기(처리 없을시 아래 리스너에서 바꿔도 원래 대로 돌아와요.))
        if (subjectList!![position].is_checked) {
            check.text = "ok"
        } else {
            check.text = ""
        }


        check.setOnClickListener() {
            if (subjectList!![position].is_checked) {
                check.text = ""
            } else {
                check.text = "ok"
            }
            subjectList!![position].is_checked = !subjectList!![position].is_checked

            //TODO: 여러가지 방법이 있는데 여기서는 간단하게 진행해보겠습니다.


        }
        return view
    }

    override fun getItem(position: Int): Any {
        return subjectList!![position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return subjectList!!.size
    }
}
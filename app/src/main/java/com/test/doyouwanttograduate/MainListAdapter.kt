package com.test.doyouwanttograduate

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import javax.security.auth.Subject

class MainListAdapter(val context: Context, val subjectList: ArrayList<com.test.doyouwanttograduate.Subject>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item, null)

        val name = view.findViewById<TextView>(R.id.subjectNameTv)
        val bsm = view.findViewById<TextView>(R.id.subjectBsmTv)
        val plan = view.findViewById<TextView>(R.id.subjectPlanTv)
        val num = view.findViewById<TextView>(R.id.subjectNumTv)

        val subject = subjectList[position]

        name.text = subject.name
        bsm.text = subject.bsm
        plan.text = subject.plan
        num.text = subject.num

        return view
    }

    override fun getItem(position: Int): Any {
        return subjectList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return subjectList.size
    }
}
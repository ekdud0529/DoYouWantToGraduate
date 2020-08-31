package com.test.doyouwanttograduate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.fin_bnt
import kotlinx.android.synthetic.main.activity_main.home_bnt
import kotlinx.android.synthetic.main.activity_main.timet_bnt
import kotlinx.android.synthetic.main.add_subject.*
import kotlinx.android.synthetic.main.timetable11.*

class addActivity : AppCompatActivity() {

    var list: MutableList<Subject> = mutableListOf()
    val ref = FirebaseDatabase.getInstance().getReference()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_subject)

        // edittext에 추가할 과목 받아서 db에 저장, 불러오기 시도..try..
        name_edt.setOnClickListener() {
            val name = name_edt.text.toString().trim()
            val bsm = bsm_edt.text.toString().trim()
            val plan = plan_edt.text.toString().trim()
            val num = num_edt.text.toString().trim()
            val state = state_edt.text.toString().trim()


            if(name.isEmpty()){
                name_edt.error = "Please insert name"
                return@setOnClickListener
            }

            if(bsm.isEmpty()){
                bsm_edt.error = "Please insert bsm"
            }

            if(plan.isEmpty()){
                plan_edt.error = "Please insert plan"
            }

            if(num.isEmpty()){
                num_edt.error = "Please insert num"
            }

            if(state.isEmpty()){
                state_edt.error = "Please insert state"
            }


            val idx = ref.push().key
            val subject = Subject(idx.toString(),name,bsm,plan,num,state,false???)

            ref.child(idx.toString()).setValue(subject).addOnCompleteListener(){
                Toast.makeText(applicationContext, "complete", Toast.LENGTH_SHORT).show()
                name_edt.text.clear()
                bsm_edt.text.clear()
                plan_edt.text.clear()
                num_edt.text.clear()
                state_edt.text.clear()


                listRefresh()
            }





            add_complete.setOnClickListener {
                val intent = Intent(this, activity_timetable11::class.java)
                             // 여기서도 직전에 넘어온 timetable의 학년학기 정보를 받아서 거기로 돌아가야함!
                startActivity(intent)
            }
        }

        class lstAdp : BaseAdapter(){
            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {



            }

            override fun getItem(p0: Int): Any? {
                return null
            }

            override fun getItemId(p0: Int): Long {
                return 0
            }

            override fun getCount():Int{
                return list.size
            }
        }







        home_bnt.setOnClickListener{
            val  intent_hbnt = Intent(this@addActivity, activity_home::class.java)
            startActivity(intent_hbnt)
        }

        timet_bnt.setOnClickListener{
            val  intent_tbnt = Intent(this@addActivity, MainActivity::class.java)
            startActivity(intent_tbnt)
        }

        set_bnt.setOnClickListener{
            val  intent_fbnt = Intent(this@addActivity, activity_mng::class.java)
            startActivity(intent_fbnt)
        }
    }

    private fun listRefresh() {
        list.removeAll{true}

        var valuelistener = object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {}
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    for(rt in snapshot.children){
                        var subject = rt.getValue((Subject::class.java))
                        list.add(subject!!)

                    }
                }

                tableListView.adapter= lstAdp()   // 이건 왜 오류나는 거지? 흠?

            }
        }
        ref.addValueEventListener(valuelistener)

        // something is changed.
        Toast.makeText(applicationContext,"", Toast.LENGTH_LONG).show()
    }
}
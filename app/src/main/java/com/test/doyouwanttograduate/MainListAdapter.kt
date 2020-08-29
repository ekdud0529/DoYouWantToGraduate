package com.test.doyouwanttograduate

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.*

class MainListAdapter(
    val context: Context, val grade: Int, val semester: Int
) : BaseAdapter() {
    private var subjectList: ArrayList<Subject>? = null

    init {


        subjectList = null


    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.list_item, null) // inflater 가 getView 에 있으면 성능이 많이 하락합니다.

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

//            check.text = "ok"
//            //firebase에 보내기....
//            val database: FirebaseDatabase = FirebaseDatabase.getInstance()
//            val myRef: DatabaseReference = database.getReference("message")
//            myRef.setValue(name.text)
//

            /*myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot?) {

                    val value = dataSnapshot?.value
                   //값 받아와서 table로 보내주자. intent?
                }

                override fun onCancelled(p0: DatabaseError?) {


                }
//이부분이 firebase사용해서 가져와본건데
//여기서 하는 동작이 뭔가요? 체크 했을때요!  이렇게 체크하면 여기 내용을 보내는걸 먼저 하고 있었습니다. 근데 3일정도 공부해봤는데 사실 firebase이용방법을 잘 모르겠습니다
// 이런식으로 가져온다는건 알겠는데 과목명에 한개 가져오고, 점수가져오고, 이렇게 한개씩 하는건지? 아니면 전체적으로 하는건지
// 자바 코드로는 dataset을 통해서 하는거 같은데 코틀린...
// 궁금한점은 데이터를 어떻게 한다는건가요> 체크  버튼을 클릭하면요?
//여기에 그 내용을 옮기려구요

음..
지금 전체 과목에 대한 data 는 어디에 올라가 있나요?
그 저번에 말씀하신대로 excel 고민해봤는데 생각보다 친구들이 과안에서만 들어서 그냥 edittext로 받아오려고 합니다.
그래서 학과 과목만 firebase에 적어뒀ㅇ요!

그럼 학과 과목은 firebase 에서 가져오고 거기에 없는 과목은 추가로 넣을 수 있게 한다는 말씀 이시죠?
네네

여기서 check 버튼은

지금 화면에 보여지는 데이터는 어디 있나요ㅕ?
            }*/
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
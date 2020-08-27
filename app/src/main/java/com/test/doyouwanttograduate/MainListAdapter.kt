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
        /**여기서  firebase 에서 데이터를 조회해 오면 됩니다.
         * 만약 많은 Activity 에서 조회 해야 한다면 트레픽 문제가 발생할 수 있기 때문에 처음 앱이 켜질때 받아오거나
         * 관련 activity 단계로 진입할때만 체크해서 가져오게 하는게 좋을 것 같습니다.
         */
        subjectList = null

        //이제 여기서 부터 작업 하시면 됩니다.넵넵 그리고 한가지 더 궁급한데 있는데
        // firebase에서 리스트 작업한 데이터들이랑. edittext로 받아서 넣는 데이터들이랑 그냥 저 페이지에서 다 처리되는 건가요?
        /**
         * 원래 Activitiy 에서 check 박스를 다 체크하고 확인 버튼을 누르면 체크 박스 되어있는 데이터를 앱 내부 db 에 저장을 해줍니다.
         * 저장할때의 db 구조는 Subject class 와 동일 하면 되겠죠?
         * 다음으로 사용자가 추가하는 데이터도 Subject class 와 동일한 구조로 입력을 받으면 동일 한 table에서 두가지 데이터를 같이 처리할 수 있죠.
         * 로컬에서 작업 되구요.
         * 그러면 여기코드에서 쓸때는 child이름으로만 사용하면 되나요?
         * firebase 에서 가져오는 부분하고 혹시 사용자가 추가하는 부분은 아직 작성이 안되어 있나요?네
         * 일단 fire base 에서 데이터 가져와서 리스트로 뿌려주고 체크박스 선택후 ok 누르면 local db에 저장 되는 부분 까지 해서 작성 해드릴꼐요
         * 보고 수정할 부분 참고 해가면서 분석 하시면 될것 같습니다!네 완전 감사합니다!
         * 이 프로젝트 push 하고 마무리 할 부분 암튼 올려주시면 제가 확인하고 수정후 리퀘슽 드리겠습닏아
         *
         */
        // 여기까지 해서 git 올려두시면
        //제가 작업 하고 연락드리겠습니다.
        //fire base 계정 정보 남겨주실 수 있으시면 톡방에 남겨주세요

        // TODO: grade , semester 기반으로 firebase 에서 과목리스트를 가져와서 가져온 데이터를 subjectList 에 넣어주시면 됩니다.
        // TODO: 가져오실때 is_checked 는 모두 False 로 해주시고 subjectList 에 추가 해주세요.
        /**
         * 다음에 아래의 getView 에서 해당 체크 박스가 선택 되면 subjectList 에서 is_checked = true 로 해주세요.
         *
         * 마지막으로 체크 선택이 다되고 원 Activity 에서 저장 버튼을 누르면 is_checked 가 True인 Subject 들만 내부 db에 저장을 해주세요.
         *
         */
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
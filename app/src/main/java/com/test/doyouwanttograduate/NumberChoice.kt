package com.test.doyouwanttograduate

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_number_choice.*

class NumberChoice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_choice)
        //TODO: 저번에 말씀 드린것 처럼 이페이지는 처음 사용자 에게만 표시하도록 하는것이 구성상 좋아보입니다.
        // activity splash 에서 아래의 grade 값을 체크해서 없으면 이 activity로 아니면 바로 activity_home 으로 가게 하는것이 좋아보입니다.

        //버튼 레이아웃 정보 매칭 시키기.
        val num14 = findViewById<Button>(R.id.num14)
        val num17 = findViewById<Button>(R.id.num17)
        val num19 = findViewById<Button>(R.id.num19)
        val num20 = findViewById<Button>(R.id.num20)

        /*
        val db = Room.databaseBuilder(
            applicationContext,
            numDB::class.java, "numDB"
        ).build()
           */

        num14.setOnClickListener {
            /* TODO: 확인 버튼을 나눌 필요가 없어보입니다.
            추가적으로 db 를 만들어 데이터를 저장할 필요 없습니다.
            단순하게 저장할 수 있는 값을 가지고 있기 때문에 쉽게 사용이 가능한 Sharedpreference 로 충분합니다.
            number_complete.setOnClickListener {
                val number = numEnt(1, 14)
                db.numDao().insert(num = number)

                startActivity(intent_h)
            }
             */
        OnYearClickListener(applicationContext)
        }
        // 비슷한 동작을 하므로 같은리스너에 할당해서 같이 관리합니다.
        num17.setOnClickListener(OnYearClickListener(applicationContext))
        num19.setOnClickListener(OnYearClickListener(applicationContext))
        num20.setOnClickListener(OnYearClickListener(applicationContext))

        /*
        number_complete.setOnClickListener {
            /**
             * TODO : 위의 학번 선택 버튼과 다를바가 없어 보입니다.
             *
             * !위의 학번 버튼들을 클릭시에 임시 변수에 할당해두고
             * 선택된 버튼에 하이라이트를 주는 방식으로 진행하는것이 좋아보입니다.
             *
             * 주석 처리된 코드들을 체크 해보세요.
             *
             */

            startActivity(intent_h)
        }

         */

    }

    class OnYearClickListener(val context:Context) : View.OnClickListener {
        override fun onClick(v: View?) {

            val sharedPreferences = context.getSharedPreferences("setting", Context.MODE_PRIVATE)
            val editor : SharedPreferences.Editor = sharedPreferences.edit()

            when(v!!.id){
                // TODO : 각 버튼 별로 save 를 다르게 해주시면 됩니다!
                //grade 값에 사용자 정보를 저장, 나중에 수정할때에도 grade 값을 수정해주면 됩니다.
                R.id.num14 ->editor.putInt("grade", 14)
                R.id.num17 -> editor.putInt("grade", 17)
                R.id.num19 -> editor.putInt("grade", 19)
                R.id.num20 -> editor.putInt("grade", 20)
            }

            if (editor.commit()){
                val intent_h = Intent(context, activity_home::class.java)
                context.startActivity(intent_h)
                //TODO: 저장이 되는경우 다음화면으로 넘어간다.
            }
        }
    }
}
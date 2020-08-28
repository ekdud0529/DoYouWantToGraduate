package com.test.doyouwanttograduate

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class NumberChoice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_choice)

        //버튼 레이아웃 정보 매칭 시키기.
        val num14 = findViewById<Button>(R.id.num14)
        val num17 = findViewById<Button>(R.id.num17)
        val num19 = findViewById<Button>(R.id.num19)
        val num20 = findViewById<Button>(R.id.num20)

        num14.setOnClickListener(OnYearClickListener(applicationContext))
        num17.setOnClickListener(OnYearClickListener(applicationContext))
        num19.setOnClickListener(OnYearClickListener(applicationContext))
        num20.setOnClickListener(OnYearClickListener(applicationContext))

    }

    class OnYearClickListener(val context:Context) : View.OnClickListener {
        override fun onClick(v: View?) {

            val sharedPreferences = context.getSharedPreferences("setting", Context.MODE_PRIVATE)
            val editor : SharedPreferences.Editor = sharedPreferences.edit()

            when(v!!.id){
                //grade 값에 사용자 정보를 저장, 나중에 수정할때에도 grade 값을 수정해주면 됩니다.
                R.id.num14 -> editor.putInt("grade", 14)
                R.id.num17 -> editor.putInt("grade", 17)
                R.id.num19 -> editor.putInt("grade", 19)
                R.id.num20 -> editor.putInt("grade", 20)
            }

            if (editor.commit()){
                val intent_h = Intent(context, activity_home::class.java)
                context.startActivity(intent_h)
            }
        }
    }


}
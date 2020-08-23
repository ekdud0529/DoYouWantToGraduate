package com.test.doyouwanttograduate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_number_choice.*

class NumberChoice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_choice)

        val intent_h = Intent(this@NumberChoice, activity_home::class.java)

        val db = Room.databaseBuilder(
            applicationContext,
            numDB::class.java, "numDB"
        ).build()


        num14.setOnClickListener{
            number_complete.setOnClickListener{
                val number = numEnt(1, 14)
                db.numDao().insert(num = number)

                startActivity(intent_h)
            }
        }

        number_complete.setOnClickListener{

            startActivity(intent_h)
        }

    }
}
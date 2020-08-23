package com.test.doyouwanttograduate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class activity_mng : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.credit_management)


//
//        val database = Firebase.database
//        val numRef = database.getReference("number")
//        numRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                val value = dataSnapshot.getValue<Int>()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//        })
//
//





        home_bnt.setOnClickListener{
            val  intent_hbnt = Intent(this@activity_mng, activity_home::class.java)
            startActivity(intent_hbnt)
        }

        timet_bnt.setOnClickListener{
            val  intent_tbnt = Intent(this@activity_mng, activity_home::class.java)
            startActivity(intent_tbnt)
        }

        fin_bnt.setOnClickListener{
            val  intent_fbnt = Intent(this@activity_mng, activity_mng::class.java)
            startActivity(intent_fbnt)
        }

    }
}
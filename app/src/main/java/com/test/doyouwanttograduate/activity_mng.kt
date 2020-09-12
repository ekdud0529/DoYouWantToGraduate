package com.test.doyouwanttograduate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
//import com.google.firebase.database.*
//import com.google.firebase.database.ktx.database
//import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.credit_management.*

class activity_mng : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.credit_management)

        val pref = getSharedPreferences("setting", Context.MODE_PRIVATE)
        val number: Int = pref.getInt("grade", 0)

        Log.d("Test grade", "$number")

        /*
        val database = Firebase.database
        var numRef = database.reference
        numRef = numRef.child("number").child("$number")

        numRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(num: DataSnapshot) {
                Log.d("Test value", "${num.value}")

                val electMin = num.child("electmin").value as Long
                val electMax = num.child("electmax").value as Long
                val majMin = num.child("majmin").value as Long
                val majrqMin = num.child("majrqmin").value as Long

                elect2.setText("${electMin / electMax}")
                majorrq2.setText("$majrqMin")
                major2.setText("$majMin")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("activity_mng", "Failed to read value", error.toException())
            }
        })
*/

        home_bnt.setOnClickListener {
            val intent_hbnt = Intent(this@activity_mng, activity_home::class.java)
            startActivity(intent_hbnt)
            overridePendingTransition(0, 0)
        }

        fin_bnt.setOnClickListener {
            val intent_fbnt = Intent(this@activity_mng, activity_mng::class.java)
            startActivity(intent_fbnt)
            overridePendingTransition(0, 0)
        }

        set_bnt.setOnClickListener {
            val intent_setbnt = Intent(this@activity_mng, activity_setting::class.java)
            startActivity(intent_setbnt)
            overridePendingTransition(0, 0)
        }

    }
}
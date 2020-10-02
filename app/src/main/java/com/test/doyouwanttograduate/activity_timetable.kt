package com.test.doyouwanttograduate

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.timetable.*
import kotlinx.android.synthetic.main.timetable.fin_bnt
import kotlinx.android.synthetic.main.timetable.g_s_complete
import kotlinx.android.synthetic.main.timetable.grade_selector
import kotlinx.android.synthetic.main.timetable.home_bnt
import kotlinx.android.synthetic.main.timetable.semester_selector
import kotlinx.android.synthetic.main.timetable.set_bnt
import java.lang.Exception

class activity_timetable : AppCompatActivity() {
    var mdb: AppDatabase? = null

    var selectedGrade: Int? = null
    var selectedSemester: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timetable)

        selectedGrade = intent.getIntExtra("selected_grade", 0)
        selectedSemester = intent.getIntExtra("selected_semester", 0)


        big_title.text = "class ${selectedGrade.toString()} - ${selectedSemester.toString()}"

        /** < 학년 학기 spinner >  **/
        val grade = resources.getStringArray(R.array.grade)
        val semester = resources.getStringArray(R.array.semester)

        val adapt1 = ArrayAdapter<String>(
            this@activity_timetable,
            android.R.layout.simple_list_item_1,
            grade
        )
        val adapt2 = ArrayAdapter<String>(
            this@activity_timetable,
            android.R.layout.simple_list_item_1,
            semester
        )

        grade_selector.adapter = adapt1
        semester_selector.adapter = adapt2


        // 학년 학기 선택.
        grade_selector.setSelection(selectedGrade!!, true)
        semester_selector.setSelection(selectedSemester!!, true)

        //스피너 선택.
        grade_selector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedGrade = p2
            }

        }
        semester_selector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedSemester = p2
            }
        }

        //Database.
        mdb = AppDatabase.getInstance(applicationContext)
        //처음 보여지는 데이터.
        loadData(
            mdb!!,
            selectedGrade!!,
            selectedSemester!!
        )

        // 데이터 가져오기.
        g_s_complete.setOnClickListener {
            loadData(
                mdb!!,
                selectedGrade!!,
                selectedSemester!!
            )
        }


        //기타 과목 입력 받는 화면으로 이동
        etc_add.setOnClickListener {
            val intent_etc = Intent(this@activity_timetable, addActivity::class.java)
            startActivity(intent_etc)
            val name =  intent.getStringExtra("name")
            val bsm = intent.getStringExtra("bsm")
            val plan =  intent.getStringExtra("plan")
            val num = intent.getIntExtra("num",0)
            val state =  intent.getStringExtra("state")

            val UserList = ArrayList<UserClass>(0)
            UserList.add(UserClass(0, name.toString(),bsm,plan,num,state, selectedGrade!!,selectedSemester!!))
            mdb!!.getDatabase().addUserClasses(*UserList.toTypedArray())
            loadData(
                mdb!!,
                selectedGrade!!,
                selectedSemester!!
            )
        }



        subject_add.setOnClickListener {
            val intentSubAdd = Intent(this@activity_timetable, SubjectListActivity::class.java)
            intentSubAdd.putExtra("selected_grade", selectedGrade!!)
            intentSubAdd.putExtra("selected_semester", selectedSemester!!)
            startActivityForResult(intentSubAdd, 1011)
        }


        delete_bt.setOnClickListener {
            
        }





        home_bnt.setOnClickListener {
            val intent_hbnt = Intent(this@activity_timetable, activity_home::class.java)
            startActivity(intent_hbnt)
            overridePendingTransition(0, 0)
        }

        set_bnt.setOnClickListener {
            val intent_tbnt = Intent(this@activity_timetable, MainActivity::class.java)
            startActivity(intent_tbnt)
        }

        fin_bnt.setOnClickListener {
            val intent_fbnt = Intent(this@activity_timetable, activity_mng::class.java)
            startActivity(intent_fbnt)
            overridePendingTransition(0, 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("LOG<receive>", "onActivityResult")

        if(resultCode != RESULT_OK){
            Toast.makeText(this,"결과 성공 아님",Toast.LENGTH_SHORT).show()
            return
        }

        //과목을 선택 했을경우,
        if (requestCode == 1011 && resultCode == Activity.RESULT_OK) {
            //데이터 갱신하기.

            loadData(
                mdb!!,
                selectedGrade!!,
                selectedSemester!!
            )
        }
    }

    override fun onDestroy() {
        AppDatabase.destroyInstance()
        super.onDestroy()
    }

    private fun loadDataAll(mdb: AppDatabase) {
        try {
            //데이터 뿌려주기.
            val dataThread = Thread(Runnable {
                val allUserClasses = mdb.getDatabase().getAllUserClasses()

                Log.d("LOG<all classes>", allUserClasses.joinToString())

                if (allUserClasses.isNotEmpty()) {
                    val listAdapter =
                        UserListAdapter(this, allUserClasses)

                    runOnUiThread {
                        tableListView.adapter = listAdapter
                        Toast.makeText(applicationContext, "검색 완료", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(applicationContext, "가져올 데이터가 없습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }

                }

            })
            dataThread.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun loadData(mdb: AppDatabase, selected_grade: Int, selected_semester: Int) {
        try {
            //데이터 뿌려주기.
            val dataThread = Thread(Runnable {
                val allUserClasses = mdb.getDatabase()
                    .getUserClassesByGradeAndSemester(selected_grade, selected_semester)
                if (allUserClasses.isNotEmpty()) {
                    val listAdapter =
                        UserListAdapter(this, allUserClasses)

                    runOnUiThread {
                        tableListView.adapter = listAdapter
                        Toast.makeText(applicationContext, "검색 완료", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(applicationContext, "가져올 데이터가 없습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }

                }

            })
            dataThread.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
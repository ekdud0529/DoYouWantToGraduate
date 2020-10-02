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
import kotlinx.android.synthetic.main.activity_subject_list.*
import kotlinx.android.synthetic.main.activity_subject_list.g_s_complete
import kotlinx.android.synthetic.main.activity_subject_list.grade_selector
import kotlinx.android.synthetic.main.activity_subject_list.semester_selector
import kotlinx.android.synthetic.main.activity_subject_list.fin_bnt
import kotlinx.android.synthetic.main.activity_subject_list.home_bnt
import kotlinx.android.synthetic.main.activity_subject_list.set_bnt
import kotlinx.android.synthetic.main.timetable.*
import java.lang.Exception


class SubjectListActivity : AppCompatActivity() {

    var selectedGrade: Int? = null
    var selectedSemester: Int? = null
    var mdb: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject_list)

        selectedGrade = intent.getIntExtra("selected_grade", 0)
        selectedSemester = intent.getIntExtra("selected_semester", 0)


        /** < 학년 학기 spinner >  **/
        val grade = resources.getStringArray(R.array.grade)
        val semester = resources.getStringArray(R.array.semester)

        val adapt1 = ArrayAdapter<String>(
            this@SubjectListActivity,
            android.R.layout.simple_list_item_1,
            grade

        )
        val adapt2 = ArrayAdapter<String>(
            this@SubjectListActivity,
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

        //데이터베이스 init.
        mdb = AppDatabase.getInstance(applicationContext)


        //처음 들어갈 때 데이터 미리 뿌리기.
        //전체를 뿌려주려면 loadDataAll(mdb!!)
        loadData(mdb!!, selectedGrade!!, selectedSemester!!)

        //선택시 해당 데이터 가져오기.
        g_s_complete.setOnClickListener {
            loadData(mdb!!, selectedGrade!!, selectedSemester!!)
        }

        choice_click.setOnClickListener {
            //TODO: 체크리스트 확인.
            if (mainListView.adapter != null) {
                val checkedClasses =
                    (mainListView.adapter as MainListAdapter).getCheckedClassesAsUserClass()
                Log.d("LOG<checked classes>", checkedClasses.joinToString())

                if (checkedClasses.isNotEmpty()) {

                    Thread(Runnable {

                        mdb!!.getDatabase().addUserClasses(*checkedClasses.toTypedArray())


                        //이전 activity 로 돌아가기.
                        setResult(Activity.RESULT_OK)
                        finish()
                        overridePendingTransition(0, 0)

                        runOnUiThread {
                            Toast.makeText(applicationContext, "추가 되었습니다.", Toast.LENGTH_SHORT)
                                .show()
                        }


                    }).start()


                } else {
                    Toast.makeText(applicationContext, "선택된 데이터가 없습니다.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "학년, 학기를 선택해주세요.", Toast.LENGTH_SHORT).show()
            }
        }


        home_bnt.setOnClickListener {
            val intent_hbnt = Intent(this@SubjectListActivity, activity_home::class.java)
            startActivity(intent_hbnt)
        }

        set_bnt.setOnClickListener {
            val intent_tbnt = Intent(this@SubjectListActivity, MainActivity::class.java)
            startActivity(intent_tbnt)
        }

        fin_bnt.setOnClickListener {
            val intent_fbnt = Intent(this@SubjectListActivity, activity_mng::class.java)
            startActivity(intent_fbnt)
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
                val allMainClasses = mdb.getDatabase()
                    .getAllMainClasses()
                if (allMainClasses.isNotEmpty()) {
                    val listAdapter =
                        MainListAdapter(this, allMainClasses)

                    runOnUiThread {
                        mainListView.adapter = listAdapter
                        Toast.makeText(applicationContext, "검색 완료", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(applicationContext, "가져올 데이터가 없습니다.", Toast.LENGTH_SHORT)
                        .show()
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
                val allMainClasses = mdb.getDatabase()
                    .getMainClassesByGradeAndSemester(selected_grade, selected_semester)
                if (allMainClasses.isNotEmpty()) {
                    val listAdapter =
                        MainListAdapter(this, allMainClasses)

                    runOnUiThread {
                        mainListView.adapter = listAdapter
                        Toast.makeText(applicationContext, "검색 완료", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(applicationContext, "가져올 데이터가 없습니다.", Toast.LENGTH_SHORT)
                        .show()
                }

            })
            dataThread.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
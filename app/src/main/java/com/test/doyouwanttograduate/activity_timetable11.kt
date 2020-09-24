package com.test.doyouwanttograduate

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.fin_bnt
import kotlinx.android.synthetic.main.activity_main.home_bnt
import kotlinx.android.synthetic.main.timetable11.*
import kotlinx.android.synthetic.main.timetable11.g_s_complete
import kotlinx.android.synthetic.main.timetable11.grade_sel
import kotlinx.android.synthetic.main.timetable11.semester_sel
import kotlinx.android.synthetic.main.timetable11.set_bnt


@Suppress("UNCHECKED_CAST")
class activity_timetable11 : AppCompatActivity() {

    private var dbList = listOf<Db>()
    private var dbDb : AppDatabase? = null

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timetable11)

        /** < 학년 학기 spinner >  **/

        val gradeEtc = resources.getStringArray(R.array.grade)
        val semester = resources.getStringArray(R.array.semester)

        val adapt1 = ArrayAdapter<String>(this@activity_timetable11, android.R.layout.simple_list_item_1, gradeEtc)
        val adapt2 = ArrayAdapter<String>(this@activity_timetable11, android.R.layout.simple_list_item_1, semester)

        grade_sel.adapter = adapt1
        semester_sel.adapter = adapt2

        /** --------------------  **/


        var table_items: List<Db>? = dbList
        var listAdapter = MainListAdapter(this, table_items as ArrayList<Subject>)


       g_s_complete.setOnClickListener{
            dbDb = AppDatabase.getInstance(this)

            /** <학년 학기 db 넘겨주기> **/
            val Runnable = Runnable {
                val new_gradeDb = grade_Db()
                val new_semDb = sem_Db()
                new_gradeDb.grade = grade_sel.selectedItem.toString()
                new_semDb.semester = semester_sel.selectedItem.toString()
                dbDb?.dbDao()?.insert_grade(new_gradeDb)
                dbDb?.dbDao()?.insert_semester(new_semDb)
            }

           val Thread = Thread(Runnable)
           Thread.start()
           finish()
            /**-------------------**/

            /** 리스트 db 가져와서 리스트뷰에 뿌려줌 **/


            val r = Runnable {
                dbList = dbDb?.dbDao()?.getAll()!!
                var listAdapter = MainListAdapter(this, table_items as ArrayList<Subject>)
                table_items = dbList
                listAdapter.notifyDataSetChanged()
                tableListView.adapter = listAdapter
            }

            val thread = Thread(r)
            thread.start()

            /**-------------------**/


        }







        //기타 과목 입력 받는 화면으로 이동
        etc_add.setOnClickListener{
            val intent_etc = Intent(this@activity_timetable11, addActivity::class.java)
            startActivity(intent_etc)
        }


        subject_add.setOnClickListener{
            val intent_subadd = Intent(this@activity_timetable11, SubjectListActivity::class.java)
            startActivity(intent_subadd)
        }


        delete_bt.setOnClickListener{
            // timetable내용 지우기 ㄱㄱ
        }





        home_bnt.setOnClickListener{
            val  intent_hbnt = Intent(this@activity_timetable11, activity_home::class.java)
            startActivity(intent_hbnt)
            overridePendingTransition(0, 0)
        }

        set_bnt.setOnClickListener{
            val  intent_tbnt = Intent(this@activity_timetable11, MainActivity::class.java)
            startActivity(intent_tbnt)
        }

        fin_bnt.setOnClickListener{
            val  intent_fbnt = Intent(this@activity_timetable11, activity_mng::class.java)
            startActivity(intent_fbnt)
            overridePendingTransition(0, 0)
        }



    }

    override fun onDestroy() {
        AppDatabase.destroyInstance()
        super.onDestroy()
    }
}
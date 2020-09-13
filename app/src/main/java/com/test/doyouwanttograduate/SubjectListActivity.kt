package com.test.doyouwanttograduate

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_subject_list.*
import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.poifs.filesystem.POIFSFileSystem
import java.io.InputStream


class SubjectListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject_list)


        //학기 학년 선택 스피너
        val gradeEtc = resources.getStringArray(R.array.grade)
        val semester = resources.getStringArray(R.array.semester)

        val adapt1 = ArrayAdapter<String>(this@SubjectListActivity, android.R.layout.simple_list_item_1, gradeEtc)
        val adapt2 = ArrayAdapter<String>(this@SubjectListActivity, android.R.layout.simple_list_item_1, semester)

        grade_sel.adapter = adapt1
        semester_sel.adapter = adapt2



        //엑셀파일 불러오기 예외처리
        try {

            //확인 버튼 누르면 -> 지정된 과목 리스트 뿌리기
            g_s_complete.setOnClickListener(){
                val myInput: InputStream
                // assetManager 초기 설정
                val assetManager = assets
                //  엑셀 시트 열기
                myInput = assetManager.open("subject.xls")
                // POI File System 객체 만들기
                val myFileSystem = POIFSFileSystem(myInput)
                //워크 북
                val myWorkBook = HSSFWorkbook(myFileSystem)
                // 워크북에서 시트 가져오기
                val sheet = myWorkBook.getSheetAt(0)

                //행을 반복할 변수 만들어주기
                val rowIter = sheet.rowIterator()
                //행 넘버 변수 만들기
                var rowno = 0

                //MutableList 생성
                val xls_items: MutableList<Subject> = mutableListOf()

                //행 반복문
                while (rowIter.hasNext()) {
                    val myRow = rowIter.next() as HSSFRow
                    if (rowno != 0) {
                        //열을 반복할 변수 만들어주기
                        val cellIter = myRow.cellIterator()
                        //열 넘버 변수 만들기
                        var colno = 0
                        var name = ""
                        var bsm = ""
                        var plan = ""
                        var num = ""
                        var state = ""
                        var checked = false
                        var get_grade = ""
                        var get_semester = ""

                        //열 반복문
                        while (cellIter.hasNext()) {
                            val myCell = cellIter.next() as HSSFCell
                            if (colno == 0) {//2번째 열이라면,
                                name = myCell.toString()
                            }
                            else if (colno == 1) {//3번째 열이라면,
                                bsm = myCell.toString()
                            }
                            else if ( colno == 2) {
                                plan = myCell.toString()
                            }
                            else if ( colno == 3) {
                                num = myCell.toString()
                            }
                            else if ( colno == 4) {
                                state = myCell.toString()
                            }
                            else if ( colno == 6) {
                                get_grade = myCell.toString()
                            }
                            else if ( colno == 7) {
                                get_semester = myCell.toString()
                            }
                            colno++
                        }

                        // 해당 학년학기 리스트에 엑셀 리스트 add
                        if(get_grade == grade_sel.selectedItem && get_semester == semester_sel.selectedItem){
                            xls_items.add(Subject(name, bsm, plan, num, state, checked, get_grade, get_semester))
                        }
                    }
                    rowno++
                }
                Log.e("checking", " items: " + xls_items);


                val listAdapter = MainListAdapter(this, xls_items as ArrayList<Subject>)
                mainListView.adapter = listAdapter

                //체크된 리스트 table로 뿌리기
                choice_click.setOnClickListener(){
                    val sharedPreferences = getSharedPreferences("setting", Context.MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()

                    val count: Int = listAdapter.getCount()

                    for (i in count - 1 downTo 0) {
                        if (xls_items[i].is_checked) {
                            editor.putString("name", xls_items[i].name)
                            editor.putString("bsm", xls_items[i].bsm)
                            editor.putString("plan", xls_items[i].plan)
                            editor.putString("num", xls_items[i].num)
                            editor.putString("state", xls_items[i].state)
                            editor.putString("grade", xls_items[i].grade)
                            editor.putString("semester", xls_items[i].semester)
                        }
                    }


                    // 모든 선택 상태 초기화.
                    mainListView.clearChoices()

                    listAdapter.notifyDataSetChanged()
                }
            }


        } catch (e: Exception) {
            Toast.makeText(this, "에러 발생", Toast.LENGTH_LONG).show()
        }



        // 추가해야되는거 : 과목 여러개 선택하고 확인 누르면 그 내용이 timetable_??로 가는거 (넘어온 timetable_학년학기 정보를 저장해야할거 같음!)


        home_bnt.setOnClickListener {
            val intent_hbnt = Intent(this@SubjectListActivity, activity_home::class.java)
            startActivity(intent_hbnt)
        }

        timet_bnt.setOnClickListener {
            val intent_tbnt = Intent(this@SubjectListActivity, MainActivity::class.java)
            startActivity(intent_tbnt)
        }

        fin_bnt.setOnClickListener {
            val intent_fbnt = Intent(this@SubjectListActivity, activity_mng::class.java)
            startActivity(intent_fbnt)
        }

    }

}
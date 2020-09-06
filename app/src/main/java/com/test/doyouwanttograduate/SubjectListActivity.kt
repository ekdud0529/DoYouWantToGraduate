package com.test.doyouwanttograduate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_subject_list.*
import kotlinx.android.synthetic.main.activity_subject_list.fin_bnt
import kotlinx.android.synthetic.main.activity_subject_list.home_bnt
import kotlinx.android.synthetic.main.activity_subject_list.timet_bnt
import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.poifs.filesystem.POIFSFileSystem
import java.io.InputStream


class SubjectListActivity : AppCompatActivity() {

   /* var subjectList_11 = arrayListOf<com.test.doyouwanttograduate.Subject>(

        Subject("수학1", "o","0","3","교양","false"),
        Subject("일반물리학1", "o","0","3","교양",false),
        Subject("일반물리학실험1", "o","0","1","교양",false),
        Subject("실용영어", "x","0","3","교양",false),
        Subject("프리젠테이션기법의이해" , "x","0","3","교양",false),
        Subject("철학의이해", "x","0","3","교양",false),
        Subject("컴퓨터프로그래밍의기초", "x","0","3","교양",false)

    )

    var subjectList_12 = arrayListOf<com.test.doyouwanttograduate.Subject>(

        Subject("수학2", "o","0","3","교양",false),
        Subject("영어회화", "x","0","3","교양",false),
        Subject("글쓰기", "x","0","3","교양",false),
        Subject("경영과창업의이해", "x","0","3","교양",false),
        Subject("c언어기초" , "x","0","3","교양",false),
        Subject("디지털논리공학(~18)", "x","0","3","교양",false),
        Subject("디지털논리설계(19~)", "x","0","3","전선",false)

    )  */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject_list)

        //학기 학년 선택 스피너
        val gradeEtc = resources.getStringArray(R.array.gradeEtc)
        val semester = resources.getStringArray(R.array.semester)

        val adapt1 = ArrayAdapter<String>(this@SubjectListActivity, android.R.layout.simple_list_item_1, gradeEtc)
        val adapt2 = ArrayAdapter<String>(this@SubjectListActivity, android.R.layout.simple_list_item_1, semester)

        grade_sel.adapter = adapt1
        semester_sel.adapter = adapt2



        //엑셀파일 불러오기 예외처리
        try {
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
            var xls_items11: MutableList<Subject> = mutableListOf()
            var xls_items12: MutableList<Subject> = mutableListOf()
            var xls_items21: MutableList<Subject> = mutableListOf()
            var xls_items22: MutableList<Subject> = mutableListOf()
            var xls_items31: MutableList<Subject> = mutableListOf()
            var xls_items32: MutableList<Subject> = mutableListOf()
            var xls_items41: MutableList<Subject> = mutableListOf()
            var xls_items42: MutableList<Subject> = mutableListOf()

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

                    //열 반복문
                    while (cellIter.hasNext()) {
                        val myCell = cellIter.next() as HSSFCell
                        if (colno == 1) {//2번째 열이라면,
                            name = myCell.toString()
                        }
                        else if (colno == 2) {//3번째 열이라면,
                            bsm = myCell.toString()
                        }
                        else if ( colno == 3) {
                            plan = myCell.toString()
                        }
                        else if ( colno == 4) {
                            num = myCell.toString()
                        }
                        else if ( colno == 5) {
                            state = myCell.toString()
                        }

                        colno++
                    }

                    if(rowno >= 1 && rowno <= 12){
                        xls_items11.add(Subject(name, bsm, plan, num, state, checked))
                    }
                    else if(rowno >= 13 && rowno <= 20){
                        xls_items12.add(Subject(name, bsm, plan, num, state, checked))
                    }
                    else if(rowno >= 21 && rowno <= 26){
                        xls_items21.add(Subject(name, bsm, plan, num, state, checked))
                    }
                    else if(rowno >= 27 && rowno <= 32){
                        xls_items22.add(Subject(name, bsm, plan, num, state, checked))
                    }
                    else if(rowno >= 33 && rowno <= 38){
                        xls_items31.add(Subject(name, bsm, plan, num, state, checked))
                    }
                    else if(rowno >= 39 && rowno <= 44){
                        xls_items32.add(Subject(name, bsm, plan, num, state, checked))
                    }
                    else if(rowno >=45 && rowno <= 51){
                        xls_items41.add(Subject(name, bsm, plan, num, state, checked))
                    }
                    else if(rowno >= 52 && rowno <= 57){
                        xls_items42.add(Subject(name, bsm, plan, num, state, checked))
                    }

                }
                rowno++
            }
            Log.e("checking", " items: " + xls_items11);






            g_s_complete.setOnClickListener(){
                if(grade_sel.selectedItem == "1학년") {
                    if(semester_sel.selectedItem == "1학기") {
                        val listAdapter = MainListAdapter(this, xls_items11 as ArrayList<Subject>)
                        mainListView.adapter = listAdapter
                    }
                }
                if(grade_sel.selectedItem == "1학년") {
                    if(semester_sel.selectedItem == "2학기") {
                        val listAdapter = MainListAdapter(this, xls_items12 as ArrayList<Subject>)
                        mainListView.adapter = listAdapter
                    }
                }
                if(grade_sel.selectedItem == "2학년") {
                    if(semester_sel.selectedItem == "1학기") {
                        val listAdapter = MainListAdapter(this, xls_items21 as ArrayList<Subject>)
                        mainListView.adapter = listAdapter
                    }
                }
                if(grade_sel.selectedItem == "2학년") {
                    if(semester_sel.selectedItem == "2학기") {
                        val listAdapter = MainListAdapter(this, xls_items22 as ArrayList<Subject>)
                        mainListView.adapter = listAdapter
                    }
                }
                if(grade_sel.selectedItem == "3학년") {
                    if(semester_sel.selectedItem == "1학기") {
                        val listAdapter = MainListAdapter(this, xls_items31 as ArrayList<Subject>)
                        mainListView.adapter = listAdapter
                    }
                }
                if(grade_sel.selectedItem == "3학년") {
                    if(semester_sel.selectedItem == "2학기") {
                        val listAdapter = MainListAdapter(this, xls_items32 as ArrayList<Subject>)
                        mainListView.adapter = listAdapter
                    }
                }
                if(grade_sel.selectedItem == "4학년") {
                    if(semester_sel.selectedItem == "1학기") {
                        val listAdapter = MainListAdapter(this, xls_items41 as ArrayList<Subject>)
                        mainListView.adapter = listAdapter
                    }
                }
                if(grade_sel.selectedItem == "4학년") {
                    if(semester_sel.selectedItem == "2학기") {
                        val listAdapter = MainListAdapter(this, xls_items42 as ArrayList<Subject>)
                        mainListView.adapter = listAdapter
                    }
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
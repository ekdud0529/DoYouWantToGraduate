package com.test.doyouwanttograduate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_subject_list.*
import kotlinx.android.synthetic.main.activity_subject_list.fin_bnt
import kotlinx.android.synthetic.main.activity_subject_list.g_s_complete
import kotlinx.android.synthetic.main.activity_subject_list.grade_sel
import kotlinx.android.synthetic.main.activity_subject_list.home_bnt
import kotlinx.android.synthetic.main.activity_subject_list.semester_sel
import kotlinx.android.synthetic.main.credit_management.view.*
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.android.synthetic.main.timetable11.*
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
            val xls_items11: MutableList<Subject> = mutableListOf()
            val xls_items12: MutableList<Subject> = mutableListOf()
            val xls_items21: MutableList<Subject> = mutableListOf()
            val xls_items22: MutableList<Subject> = mutableListOf()
            val xls_items31: MutableList<Subject> = mutableListOf()
            val xls_items32: MutableList<Subject> = mutableListOf()
            val xls_items41: MutableList<Subject> = mutableListOf()
            val xls_items42: MutableList<Subject> = mutableListOf()

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

                        colno++
                    }

                    if(rowno >= 0 && rowno <= 12){
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




            //해당 학년, 학기 따라 지정된 과목 리스트 뿌리기
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





            //체크된 리스트 table로 뿌리기
            choice_click.setOnClickListener() {

                val items11: MutableList<Subject> = mutableListOf()
                val items12: MutableList<Subject> = mutableListOf()
                val items21: MutableList<Subject> = mutableListOf()
                val items22: MutableList<Subject> = mutableListOf()
                val items31: MutableList<Subject> = mutableListOf()
                val items32: MutableList<Subject> = mutableListOf()
                val items41: MutableList<Subject> = mutableListOf()
                val items42: MutableList<Subject> = mutableListOf()

                val secondIntent = intent
                val grade = secondIntent.getStringExtra("grade")
                val sem = secondIntent.getStringExtra("semester")


                // 받아온 학년, 학기에 따라
                if(grade == "1학년") {
                    if(sem == "1학기") {
                        //내가 둘러보고 있는 지정 과목 리스트 학년 학기에 따라
                        if(grade_sel.selectedItem == "1학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                // 체크된 과목들을 확인
                                for (i in xls_items11.size - 1 downTo 0) {
                                    if (xls_items11[i].is_checked) {
                                        //체크됐으면 리스트에 추가
                                        items11.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "1학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items12.size - 1 downTo 0) {
                                    if (xls_items12 [i].is_checked) {
                                        items11.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 2학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "2학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items21.size - 1 downTo 0) {
                                    if (xls_items21[i].is_checked) {
                                        items11.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("2학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "2학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items22.size - 1 downTo 0) {
                                    if (xls_items22[i].is_checked) {
                                        items11.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("2학년 2학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "3학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items31.size - 1 downTo 0) {
                                    if (xls_items31[i].is_checked) {
                                        items11.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("3학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "3학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items32.size - 1 downTo 0) {
                                    if (xls_items32[i].is_checked) {
                                        items11.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("3학년 2학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "4학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items41.size - 1 downTo 0) {
                                    if (xls_items41[i].is_checked) {
                                        items11.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("4학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "4학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items42.size - 1 downTo 0) {
                                    if (xls_items42[i].is_checked) {
                                        items11.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("4학년 2학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }

                        // 다 추가하고 그걸 timetable리스트뷰에 뿌리기
                        val listAdapter = MainListAdapter(this, items11 as ArrayList<Subject>)
                        tableListView.adapter = listAdapter
                        //tableListView.choiceMode = ListView.CHOICE_MODE_MULTIPLE

                        listAdapter.notifyDataSetChanged()

                        // 선택 초기화
                        mainListView.clearChoices()


                    }
                }
                if(grade == "1학년") {
                    if(sem == "2학기") {
                        if(grade_sel.selectedItem == "1학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items11.size - 1 downTo 0) {
                                    if (xls_items11[i].is_checked) {
                                        items12.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "1학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items12.size - 1 downTo 0) {
                                    if (xls_items12 [i].is_checked) {
                                        items12.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 2학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "2학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items21.size - 1 downTo 0) {
                                    if (xls_items21[i].is_checked) {
                                        items12.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("2학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "2학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items22.size - 1 downTo 0) {
                                    if (xls_items22[i].is_checked) {
                                        items12.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("2학년 2학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "3학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items31.size - 1 downTo 0) {
                                    if (xls_items31[i].is_checked) {
                                        items12.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("3학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "3학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items32.size - 1 downTo 0) {
                                    if (xls_items32[i].is_checked) {
                                        items12.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("3학년 2학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "4학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items41.size - 1 downTo 0) {
                                    if (xls_items41[i].is_checked) {
                                        items12.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("4학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "4학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items42.size - 1 downTo 0) {
                                    if (xls_items42[i].is_checked) {
                                        items12.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("4학년 2학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }

                        val listAdapter = MainListAdapter(this, items12 as ArrayList<Subject>)
                        tableListView.adapter = listAdapter
                        //tableListView.choiceMode = ListView.CHOICE_MODE_MULTIPLE

                        listAdapter.notifyDataSetChanged()

                        // 선택 초기화
                        mainListView.clearChoices()


                    }
                }
                if(grade == "2학년") {
                    if(sem == "1학기") {
                        if(grade_sel.selectedItem == "1학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items11.size - 1 downTo 0) {
                                    if (xls_items11[i].is_checked) {
                                        items21.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "1학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items12.size - 1 downTo 0) {
                                    if (xls_items12 [i].is_checked) {
                                        items21.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "2학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items21.size - 1 downTo 0) {
                                    if (xls_items21[i].is_checked) {
                                        items21.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "2학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items22.size - 1 downTo 0) {
                                    if (xls_items22[i].is_checked) {
                                        items21.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "3학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items31.size - 1 downTo 0) {
                                    if (xls_items31[i].is_checked) {
                                        items21.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "3학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items32.size - 1 downTo 0) {
                                    if (xls_items32[i].is_checked) {
                                        items21.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "4학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items41.size - 1 downTo 0) {
                                    if (xls_items41[i].is_checked) {
                                        items21.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "4학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items42.size - 1 downTo 0) {
                                    if (xls_items42[i].is_checked) {
                                        items21.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }

                        val listAdapter = MainListAdapter(this, items22 as ArrayList<Subject>)
                        tableListView.adapter = listAdapter
                        //tableListView.choiceMode = ListView.CHOICE_MODE_MULTIPLE

                        listAdapter.notifyDataSetChanged()

                        // 선택 초기화
                        mainListView.clearChoices()
                    }
                }

                if(grade == "2학년") {
                    if(sem == "2학기") {
                        if(grade_sel.selectedItem == "1학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items11.size - 1 downTo 0) {
                                    if (xls_items11[i].is_checked) {
                                        items22.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "1학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items12.size - 1 downTo 0) {
                                    if (xls_items12 [i].is_checked) {
                                        items22.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "2학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items21.size - 1 downTo 0) {
                                    if (xls_items21[i].is_checked) {
                                        items22.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "2학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items22.size - 1 downTo 0) {
                                    if (xls_items22[i].is_checked) {
                                        items22.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "3학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items31.size - 1 downTo 0) {
                                    if (xls_items31[i].is_checked) {
                                        items22.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "3학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items32.size - 1 downTo 0) {
                                    if (xls_items32[i].is_checked) {
                                        items22.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "4학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items41.size - 1 downTo 0) {
                                    if (xls_items41[i].is_checked) {
                                        items22.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "4학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items42.size - 1 downTo 0) {
                                    if (xls_items42[i].is_checked) {
                                        items22.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }

                        val listAdapter = MainListAdapter(this, items22 as ArrayList<Subject>)
                        tableListView.adapter = listAdapter
                        //tableListView.choiceMode = ListView.CHOICE_MODE_MULTIPLE

                        listAdapter.notifyDataSetChanged()

                        // 선택 초기화
                        mainListView.clearChoices()

                    }
                }

                if(grade == "3학년") {
                    if(sem == "1학기") {
                        if(grade_sel.selectedItem == "1학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items11.size - 1 downTo 0) {
                                    if (xls_items11[i].is_checked) {
                                        items31.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "1학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items12.size - 1 downTo 0) {
                                    if (xls_items12 [i].is_checked) {
                                        items31.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "2학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items21.size - 1 downTo 0) {
                                    if (xls_items21[i].is_checked) {
                                        items31.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "2학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items22.size - 1 downTo 0) {
                                    if (xls_items22[i].is_checked) {
                                        items31.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "3학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items31.size - 1 downTo 0) {
                                    if (xls_items31[i].is_checked) {
                                        items31.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "3학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items32.size - 1 downTo 0) {
                                    if (xls_items32[i].is_checked) {
                                        items31.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "4학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items41.size - 1 downTo 0) {
                                    if (xls_items41[i].is_checked) {
                                        items31.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "4학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items42.size - 1 downTo 0) {
                                    if (xls_items42[i].is_checked) {
                                        items31.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }

                        val listAdapter = MainListAdapter(this, items31 as ArrayList<Subject>)
                        tableListView.adapter = listAdapter
                        //tableListView.choiceMode = ListView.CHOICE_MODE_MULTIPLE

                        listAdapter.notifyDataSetChanged()

                        // 선택 초기화
                        mainListView.clearChoices()
                    }
                }
                if(grade == "3학년") {
                    if(sem == "2학기") {
                        if(grade_sel.selectedItem == "1학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items11.size - 1 downTo 0) {
                                    if (xls_items11[i].is_checked) {
                                        items32.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "1학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items12.size - 1 downTo 0) {
                                    if (xls_items12 [i].is_checked) {
                                        items32.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "2학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items21.size - 1 downTo 0) {
                                    if (xls_items21[i].is_checked) {
                                        items32.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "2학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items22.size - 1 downTo 0) {
                                    if (xls_items22[i].is_checked) {
                                        items32.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "3학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items31.size - 1 downTo 0) {
                                    if (xls_items31[i].is_checked) {
                                        items32.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "3학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items32.size - 1 downTo 0) {
                                    if (xls_items32[i].is_checked) {
                                        items32.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "4학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items41.size - 1 downTo 0) {
                                    if (xls_items41[i].is_checked) {
                                        items32.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "4학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items42.size - 1 downTo 0) {
                                    if (xls_items42[i].is_checked) {
                                        items32.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }

                        val listAdapter = MainListAdapter(this, items32 as ArrayList<Subject>)
                        tableListView.adapter = listAdapter
                        //tableListView.choiceMode = ListView.CHOICE_MODE_MULTIPLE

                        listAdapter.notifyDataSetChanged()

                        // 선택 초기화
                        mainListView.clearChoices()
                    }
                }
                if(grade == "4학년") {
                    if(sem == "1학기") {
                        if(grade_sel.selectedItem == "1학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items11.size - 1 downTo 0) {
                                    if (xls_items11[i].is_checked) {
                                        items41.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "1학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items12.size - 1 downTo 0) {
                                    if (xls_items12 [i].is_checked) {
                                        items41.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "2학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items21.size - 1 downTo 0) {
                                    if (xls_items21[i].is_checked) {
                                        items41.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "2학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items22.size - 1 downTo 0) {
                                    if (xls_items22[i].is_checked) {
                                        items41.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "3학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items31.size - 1 downTo 0) {
                                    if (xls_items31[i].is_checked) {
                                        items41.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "3학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items32.size - 1 downTo 0) {
                                    if (xls_items32[i].is_checked) {
                                        items41.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "4학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items41.size - 1 downTo 0) {
                                    if (xls_items41[i].is_checked) {
                                        items41.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "4학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items42.size - 1 downTo 0) {
                                    if (xls_items42[i].is_checked) {
                                        items41.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }

                        val listAdapter = MainListAdapter(this, items41 as ArrayList<Subject>)
                        tableListView.adapter = listAdapter
                        //tableListView.choiceMode = ListView.CHOICE_MODE_MULTIPLE

                        listAdapter.notifyDataSetChanged()

                        // 선택 초기화
                        mainListView.clearChoices()
                    }
                }
                if(grade == "4학년") {
                    if(sem == "2학기") {
                        if(grade_sel.selectedItem == "1학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items11.size - 1 downTo 0) {
                                    if (xls_items11[i].is_checked) {
                                        items42.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "1학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items12.size - 1 downTo 0) {
                                    if (xls_items12 [i].is_checked) {
                                        items42.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "2학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items21.size - 1 downTo 0) {
                                    if (xls_items21[i].is_checked) {
                                        items42.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "2학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items22.size - 1 downTo 0) {
                                    if (xls_items22[i].is_checked) {
                                        items42.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "3학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items31.size - 1 downTo 0) {
                                    if (xls_items31[i].is_checked) {
                                        items42.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "3학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items32.size - 1 downTo 0) {
                                    if (xls_items32[i].is_checked) {
                                        items42.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "4학년") {
                            if(semester_sel.selectedItem == "1학기") {
                                for (i in xls_items41.size - 1 downTo 0) {
                                    if (xls_items41[i].is_checked) {
                                        items42.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }
                        if(grade_sel.selectedItem == "4학년") {
                            if(semester_sel.selectedItem == "2학기") {
                                for (i in xls_items42.size - 1 downTo 0) {
                                    if (xls_items42[i].is_checked) {
                                        items42.add(Subject(xls_items11[i].name, xls_items11[i].bsm, xls_items11[i].plan, xls_items11[i].num, xls_items11[i].state,xls_items11[i].is_checked))
                                        Log.d("1학년 1학기 선택된 아이템 추가", i.toString())
                                    }
                                }
                            }
                        }

                        val listAdapter = MainListAdapter(this, items42 as ArrayList<Subject>)
                        tableListView.adapter = listAdapter
                        //tableListView.choiceMode = ListView.CHOICE_MODE_MULTIPLE

                        listAdapter.notifyDataSetChanged()

                        // 선택 초기화
                        mainListView.clearChoices()
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

        fin_bnt.setOnClickListener {
            val intent_fbnt = Intent(this@SubjectListActivity, activity_mng::class.java)
            startActivity(intent_fbnt)
        }

        set_bnt.setOnClickListener {
            val intent_setbnt = Intent(this@SubjectListActivity, activity_setting::class.java)
            startActivity(intent_setbnt)
            overridePendingTransition(0, 0)
        }

    }


}
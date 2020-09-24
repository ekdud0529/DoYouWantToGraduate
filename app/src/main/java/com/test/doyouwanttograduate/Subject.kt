package com.test.doyouwanttograduate

class Subject(
    val name: String,
    val bsm: String,
    val plan: String,
    val num: String,
    val state: String,
    var is_checked: Boolean,
    val grade: String,
    val semester: String,
    val t_grade : String,
    val t_sem: String

) {
    //여기에 과목의 check box 선택 여부를 결정하는 변수가 있나요?
    //아니요!
    constructor() : this("", "", "", "", "" ,false,"","","","") {}
}
package com.test.doyouwanttograduate

import android.content.Context

class Subject(
    val name: String,
    val bsm: String,
    val plan: String,
    val num: String,
    val state: String,
    var is_checked: Boolean
) {
    //여기에 과목의 check box 선택 여부를 결정하는 변수가 있나요?
    //아니요!
    constructor() : this("", "", "", "", "" , false) {}
}
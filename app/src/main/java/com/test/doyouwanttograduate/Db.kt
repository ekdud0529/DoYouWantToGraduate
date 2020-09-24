package com.test.doyouwanttograduate

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_main")
data class MainClass(
    @PrimaryKey
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "bsm") var bsm: String?,
    @ColumnInfo(name = "plan") var plan: String?,
    @ColumnInfo(name = "num") var num: Int?,
    @ColumnInfo(name = "state") var state: String?,
    @ColumnInfo(name = "grade") var grade: Int,
    @ColumnInfo(name = "semester") var semester: Int,
    /*
    TODO: 사용자가 선택한 class는 지울 수 있도록 True 속성을 부여하세요.
    DB 에서 가져오는 데이터는 False 속성을 부여하세요.
     */
    @ColumnInfo(name = "removable")
    var isRemovable: Boolean
)


@Entity(tableName = "table_user")
data class UserClass(
    @PrimaryKey
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "bsm") var bsm: String?,
    @ColumnInfo(name = "plan") var plan: String?,
    @ColumnInfo(name = "num") var num: Int?,
    @ColumnInfo(name = "state") var state: String?,
    @ColumnInfo(name = "grade") var grade: Int,
    @ColumnInfo(name = "semester") var semester: Int
)


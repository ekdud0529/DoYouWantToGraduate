package com.test.doyouwanttograduate

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "db")
data class Db(@PrimaryKey var id:Long?,
              @ColumnInfo(name = "name") var name: String?,
              @ColumnInfo(name = "bsm") var bsm: String?,
              @ColumnInfo(name = "plan") var plan: String?,
              @ColumnInfo(name = "num") var num: String?,
              @ColumnInfo(name = "state") var state: String?,
              @ColumnInfo(name = "is_checked") var is_checked: Boolean?,
              @ColumnInfo(name = "grade") var grade: String?,
              @ColumnInfo(name = "semester") var semester: String?,
              @ColumnInfo(name = "grade") var t_grade: String?,
              @ColumnInfo(name = "semester") var t_semester: String?


){
    constructor(): this(null,"","","","","",false,"","","","")
}

@Entity(tableName = "grade")
data class grade_Db(@PrimaryKey var id:Long?,
                    @ColumnInfo(name = "grade") var grade: String?

){
    constructor(): this(null,"")
}
@Entity(tableName = "semester")
data class sem_Db(@PrimaryKey var id:Long?,
                    @ColumnInfo(name = "semester") var semester: String?

){
    constructor(): this(null,"")
}
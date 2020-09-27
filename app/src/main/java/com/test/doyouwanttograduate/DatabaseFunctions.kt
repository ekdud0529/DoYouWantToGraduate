package com.test.doyouwanttograduate

import androidx.room.*

@Dao
interface DatabaseFunctions {

    // TODO: for MainClass : 전체 과목 데이터
    @Query("SELECT * FROM table_main")
    fun getAllMainClasses(): List<MainClass>

    @Query("SELECT * FROM table_main where grade = (:target_grade) and semester =(:target_semester)")
    fun getMainClassesByGradeAndSemester(target_grade: Int, target_semester: Int): List<MainClass>

    @Query("SELECT * FROM table_main where grade = (:target_grade)")
    fun getMainClassesByGrade(target_grade: Int): List<MainClass>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMainClasses(vararg selected_classes: MainClass)

    @Update
    fun updateMainClass(selected_class: MainClass)

    @Delete
    fun removeMainClass(selected_class: MainClass)

    // TODO: for UserClass : 사용자 과목 데이터
    @Query("SELECT * FROM table_user")
    fun getAllUserClasses(): List<UserClass>

    @Query("SELECT * FROM table_user where grade = (:target_grade) and semester =(:target_semester)")
    fun getUserClassesByGradeAndSemester(target_grade: Int, target_semester: Int): List<UserClass>

    @Query("SELECT * FROM table_user where grade = (:target_grade)")
    fun getUserClassesByGrade(target_grade: Int): List<UserClass>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUserClasses(vararg selected_classes: UserClass)

    @Update
    fun updateUserClass(selected_class: UserClass)

    @Delete
    fun removeUserClass(selected_class: UserClass)


}
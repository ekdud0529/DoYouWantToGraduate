package com.test.doyouwanttograduate

import androidx.room.*

@Dao
interface DbDao {
    @Query("SELECT * FROM Db")
    fun getAll(): List<Db>

    @Query("SELECT * FROM Db")
    fun get_grade(): List<grade_Db>

    @Query("SELECT * FROM Db")
    fun get_sem(): List<sem_Db>

    @Insert
    fun insert(db:Db)

    @Insert
    fun insert_grade(grade: grade_Db)

    @Insert
    fun insert_semester(semester: sem_Db)

    @Update
    fun update(db:Db)

    @Delete
    fun deleteAll()
}
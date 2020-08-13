package com.test.doyouwanttograduate

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SubjDao {
    @Query("SELECT * FROM subject")
    fun getAll() : List<SubjEnt>

    @Insert()
    fun insert(subject : SubjDao)

    @Delete
    fun delete(subject : SubjEnt)

}
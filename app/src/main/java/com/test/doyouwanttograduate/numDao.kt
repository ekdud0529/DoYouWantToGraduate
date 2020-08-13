package com.test.doyouwanttograduate

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface numDao {
    @Query("Select * From number")
    fun getAll() : List<numEnt>

    @Insert
    fun insertAll(number: numEnt)

    @Delete
    fun delete(number: numEnt)
}
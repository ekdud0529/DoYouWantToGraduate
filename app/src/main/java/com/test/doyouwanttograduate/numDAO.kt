package com.test.doyouwanttograduate

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface numDAO {
    @Insert(onConflict = REPLACE)
    fun insert(num: numEnt)

    @Query("SELECT * FROM numEnt")
    fun getAll(): List<numEnt>
}
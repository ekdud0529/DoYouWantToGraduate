package com.test.doyouwanttograduate

import androidx.room.Database

@Database(entities = arrayOf(SubjEnt::class), version = 1)
abstract class SubjDatabase {
    abstract fun SubjDao() : SubjDao

    companion object{

    }
}
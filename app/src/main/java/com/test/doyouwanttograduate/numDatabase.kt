package com.test.doyouwanttograduate

import androidx.room.Database
import androidx.room.Room

@Database(entities = arrayOf(numEnt::class), version = 1)
abstract class numDatabase {
    abstract fun numDao() : numDao


}
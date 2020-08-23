package com.test.doyouwanttograduate

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [numEnt::class], version = 1)
abstract class numDB: RoomDatabase() {
    abstract fun numDao(): numDAO
}
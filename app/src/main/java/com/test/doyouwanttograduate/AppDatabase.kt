package com.test.doyouwanttograduate

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(MainClass::class, UserClass::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDatabase(): DatabaseFunctions

    companion object {
        private var INSTANCE: AppDatabase? = null

        //객체 반환
        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "db.db"
                    )
                        .createFromAsset("database/subjects.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        //객체 삭제
        fun destroyInstance() {
            INSTANCE = null
        }
    }


}
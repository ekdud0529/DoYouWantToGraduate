package com.test.doyouwanttograduate

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subject")
class SubjEnt(@PrimaryKey(autoGenerate = true)
                      val id: Int,
              var classifi: String,
              var title: String,
              var sem: Int,
              var arch: Int,
              var bsm: Int,
              var credit: Int) {

}
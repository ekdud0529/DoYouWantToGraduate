package com.test.doyouwanttograduate

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class numEnt(@PrimaryKey(autoGenerate = true) val id : Int, var num : Int) {
}
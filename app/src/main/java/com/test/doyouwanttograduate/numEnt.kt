package com.test.doyouwanttograduate

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "number")
data class numEnt(@PrimaryKey(autoGenerate = true) val id: Int,
                  @ColumnInfo val electmin: Int,
                  @ColumnInfo val electmax: Int,
                  @ColumnInfo val majrqmin: Int,
                  @ColumnInfo val majmin: Int)
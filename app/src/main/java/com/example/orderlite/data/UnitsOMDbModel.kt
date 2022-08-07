package com.example.orderlite.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "units_o_m")
data class UnitsOMDbModel(
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo val name:String,
    @ColumnInfo val shortName:String
)
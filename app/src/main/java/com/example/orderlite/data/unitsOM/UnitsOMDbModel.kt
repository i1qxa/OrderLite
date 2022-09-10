package com.example.orderlite.data.unitsOM

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "units_o_m")
data class UnitsOMDbModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "unit_id")
    val unitId:Int,
    @ColumnInfo(name = "unit_name")
    val unitName:String,
    @ColumnInfo(name = "unit_short_name")
    val unitShortName:String
)
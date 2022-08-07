package com.example.orderlite.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order")
data class OrderDbModel(
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo val date:String
)
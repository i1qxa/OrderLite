package com.example.orderlite.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_item")
data class ProductItemDbModel (
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo val defaultUnitId:Int,
    @ColumnInfo val name:String
)
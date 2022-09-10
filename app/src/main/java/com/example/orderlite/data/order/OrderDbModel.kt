package com.example.orderlite.data.order

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order")
data class OrderDbModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "order_id")
    val orderId:Int,
    @ColumnInfo(name = "order_date")
    val orderDate:String
)
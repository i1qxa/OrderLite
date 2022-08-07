package com.example.orderlite.data.orderRecord

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_record")
data class OrderRecordDbModel(
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo val orderId:Int,
    @ColumnInfo val productId:Int,
    @ColumnInfo val unitId:Int,
    @ColumnInfo val price:Double,
    @ColumnInfo val amount:Double
)
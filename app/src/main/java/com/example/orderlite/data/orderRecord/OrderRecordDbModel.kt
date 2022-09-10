package com.example.orderlite.data.orderRecord

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.orderlite.data.order.OrderDbModel

@Entity(
    tableName = "order_record",
    foreignKeys = [
        ForeignKey(
            entity = OrderDbModel::class,
            parentColumns = ["order_id"],
            childColumns = ["order_id"]
        )
    ]
)
data class OrderRecordDbModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "order_record_id")
    val orderRecordId:Int,
    @ColumnInfo(name = "order_id")
    val orderId:Int,
    @ColumnInfo(name = "product_id")
    val productId:Int,
    @ColumnInfo(name = "unit_id")
    val unitId:Int,
    @ColumnInfo(name = "price")
    val price:Double,
    @ColumnInfo(name = "amount")
    val amount:Double
)
package com.example.orderlite.domain.orderRecord

import androidx.lifecycle.LiveData

interface OrderRecordRepository {
    fun addOrderRecord(orderRecord: OrderRecord)

    fun deleteOrderRecord(orderRecordId:Int)

    fun editOrderRecord(orderRecord: OrderRecord)

    fun getOrderRecordList(orderId:Int):LiveData<List<OrderRecord>>

    fun getOrderRecord(orderRecordId:Int):OrderRecord
}
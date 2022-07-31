package com.example.orderlite.domain.orderRecord

interface OrderRecordRepository {
    fun addOrderRecord(orderRecord: OrderRecord)

    fun deleteOrderRecord(orderRecordId:Int)

    fun editOrderRecord(orderRecord: OrderRecord)

    fun getOrderRecordList(orderId:Int):List<OrderRecord>

    fun getOrderRecord(orderRecordId:Int):OrderRecord
}
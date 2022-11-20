package com.example.orderlite.domain.orderRecord

import androidx.lifecycle.LiveData

interface OrderRecordRepository {
    suspend fun addOrderRecord(orderRecord: OrderRecord)

    suspend fun deleteOrderRecord(orderRecordId:Int)

    suspend fun editOrderRecord(orderRecord: OrderRecord)

    fun getOrderRecordList(orderId:Int):LiveData<List<OrderRecord>>

    suspend fun getOrderRecord(orderId:Int, productItemId:Int):OrderRecord

    fun getOrderRecordJoinList(orderId:Int):LiveData<List<OrderRecordWithProductItemAndUnitOMItem>>

    suspend fun addListOrderRecord(listOrderRecord:List<OrderRecord>)


}
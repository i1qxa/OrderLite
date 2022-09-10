package com.example.orderlite.domain.orderRecord

import androidx.lifecycle.LiveData

class GetOrderRecordListUseCase(private val orderRecordRepository: OrderRecordRepository) {
    fun getOrderRecordList(orderId:Int):LiveData<List<OrderRecord>>{
        return orderRecordRepository.getOrderRecordList(orderId)
    }
}
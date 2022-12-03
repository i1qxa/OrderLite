package com.example.orderlite.domain.orderRecord

import androidx.lifecycle.LiveData

class GetOrderRecordLDListUseCase(private val orderRecordRepository: OrderRecordRepository) {
    fun getOrderRecordLDList(orderId:Int):LiveData<List<OrderRecord>>{
        return orderRecordRepository.getOrderRecordLDList(orderId)
    }
}
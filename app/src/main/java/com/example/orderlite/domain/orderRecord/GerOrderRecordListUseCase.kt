package com.example.orderlite.domain.orderRecord

class GerOrderRecordListUseCase(private val repository: OrderRecordRepository) {
    suspend fun getOrderRecordList(orderId:Int):List<OrderRecord>{
        return repository.getOrderRecordList(orderId)
    }
}
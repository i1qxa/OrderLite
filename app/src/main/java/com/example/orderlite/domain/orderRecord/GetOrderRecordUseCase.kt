package com.example.orderlite.domain.orderRecord

class GetOrderRecordUseCase(private val orderRecordRepository: OrderRecordRepository) {
    suspend fun getOrderRecord(orderId:Int, productItemId:Int):OrderRecord{
        return orderRecordRepository.getOrderRecord(orderId, productItemId)
    }
}
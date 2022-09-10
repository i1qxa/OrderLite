package com.example.orderlite.domain.orderRecord

class GetOrderRecordUseCase(private val orderRecordRepository: OrderRecordRepository) {
    suspend fun getOrderRecord(orderRecordId:Int):OrderRecord{
        return orderRecordRepository.getOrderRecord(orderRecordId)
    }
}
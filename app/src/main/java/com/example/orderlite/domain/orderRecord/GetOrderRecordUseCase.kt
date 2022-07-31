package com.example.orderlite.domain.orderRecord

class GetOrderRecordUseCase(private val orderRecordRepository: OrderRecordRepository) {
    fun getOrderRecord(orderRecordId:Int):OrderRecord{
        return orderRecordRepository.getOrderRecord(orderRecordId)
    }
}
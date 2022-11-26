package com.example.orderlite.domain.orderRecord

class AddListOrderRecordUseCase(private val orderRecordRepository:OrderRecordRepository) {
    suspend fun addListOrderRecord(baseOrderId:Int, additionalOrderId:Int){
        orderRecordRepository.addListOrderRecord(baseOrderId,additionalOrderId)
    }
}
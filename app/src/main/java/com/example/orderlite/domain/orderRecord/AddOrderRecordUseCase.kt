package com.example.orderlite.domain.orderRecord

class AddOrderRecordUseCase (private val orderRecordRepository: OrderRecordRepository){
    suspend fun addOrderRecord(orderRecord: OrderRecord){
        orderRecordRepository.addOrderRecord(orderRecord)
    }
}
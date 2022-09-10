package com.example.orderlite.domain.orderRecord

class EditOrderRecordUseCase (private val orderRecordRepository: OrderRecordRepository){
    suspend fun editOrderRecord(orderRecord: OrderRecord){
        orderRecordRepository.editOrderRecord(orderRecord)
    }
}
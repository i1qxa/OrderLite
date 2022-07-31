package com.example.orderlite.domain.orderRecord

class EditOrderRecordUseCase (private val orderRecordRepository: OrderRecordRepository){
    fun editOrderRecord(orderRecord: OrderRecord){
        orderRecordRepository.editOrderRecord(orderRecord)
    }
}
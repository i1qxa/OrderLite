package com.example.orderlite.domain.orderRecord

class AddListOrderRecordUseCase(private val orderRecordRepository:OrderRecordRepository) {
    suspend fun addListOrderRecord(listOrderRecord:List<OrderRecord>){
        orderRecordRepository.addListOrderRecord(listOrderRecord)
    }
}
package com.example.orderlite.domain.orderRecord

class AddListOrderRecordUseCase(private val orderRecordRepository:OrderRecordRepository) {
    suspend fun addListOrderRecord(baseListOrderRecord:List<OrderRecord>, additionalListOrderRecord:List<OrderRecord>){
        orderRecordRepository.addListOrderRecord(baseListOrderRecord,additionalListOrderRecord)
    }
}
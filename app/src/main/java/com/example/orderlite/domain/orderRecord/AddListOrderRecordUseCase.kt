package com.example.orderlite.domain.orderRecord

class AddListOrderRecordUseCase(private val orderRecordRepository:OrderRecordRepository) {
    suspend fun addListOrderRecord(baseListOrderRecord:List<OrderRecord>, additionalListOrderRecord:List<OrderRecord>, orderId:Int){
        orderRecordRepository.addListOrderRecord(baseListOrderRecord,additionalListOrderRecord, orderId)
    }
}
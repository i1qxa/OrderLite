package com.example.orderlite.domain.orderRecord

class DeleteOrderRecordUseCase (private val orderRecordRepository: OrderRecordRepository) {
    fun deleteOrderRecord(orderRecordId:Int){
        orderRecordRepository.deleteOrderRecord(orderRecordId)
    }
}
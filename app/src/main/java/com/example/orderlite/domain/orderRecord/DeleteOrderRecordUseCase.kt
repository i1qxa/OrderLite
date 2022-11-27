package com.example.orderlite.domain.orderRecord

class DeleteOrderRecordUseCase (private val orderRecordRepository: OrderRecordRepository) {
    suspend fun deleteOrderRecord(orderId:Int, productItemId:Int){
        orderRecordRepository.deleteOrderRecord(orderId, productItemId)
    }
}
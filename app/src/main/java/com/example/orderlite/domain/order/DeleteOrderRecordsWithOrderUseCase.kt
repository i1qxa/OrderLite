package com.example.orderlite.domain.order

class DeleteOrderRecordsWithOrderUseCase(private val repository: OrderRepository) {
    suspend fun deleteOrderRecordsWithOrder(id:Int){
        repository.deleteOrderRecordsWithOrder(id)
    }
}
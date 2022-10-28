package com.example.orderlite.domain.orderRecord

import androidx.lifecycle.LiveData

class GetOrderRecordWithProductItemAndUnitOMItemListUseCase(private val repository: OrderRecordRepository) {
    fun getOrderRecordJoinList(orderId:Int): LiveData<List<OrderRecordWithProductItemAndUnitOMItem>>{
        return repository.getOrderRecordJoinList(orderId)
    }
}
package com.example.orderlite.domain.orderRecord

class GetOrderRecordListUseCase(private val orderRecordRepository: OrderRecordRepository) {
    fun getOrderRecordList(orderId:Int):List<OrderRecord>{
        return orderRecordRepository.getOrderRecordList(orderId)
    }
}
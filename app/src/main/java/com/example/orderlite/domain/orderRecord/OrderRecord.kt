package com.example.orderlite.domain.orderRecord

data class OrderRecord(
    val orderRecordId:Int,
    val orderId:Int,
    val productId:Int,
    val unitId:Int,
    var price:Int,
    var amount:Double
){
    var sum = (amount*price).toInt()
}
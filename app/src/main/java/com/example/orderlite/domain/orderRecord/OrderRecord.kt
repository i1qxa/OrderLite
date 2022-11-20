package com.example.orderlite.domain.orderRecord

data class OrderRecord(
    val id:Int,
    val orderId:Int,
    val productId:Int,
    val unitId:Int,
    var price:Double,
    var amount:Double
)
package com.example.orderlite.domain

data class OrderRecord(
    val id:Int,
    val orderId:Int,
    val productId:Int,
    val unitId:Int,
    val price:Double,
    val amount:Double
)
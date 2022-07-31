package com.example.orderlite.domain.order

interface OrderRepository {
    fun getOrderList(dateStart:String, dateEnd:String):List<Order>
}
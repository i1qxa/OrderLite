package com.example.orderlite.domain.order

import androidx.lifecycle.LiveData

interface OrderRepository {
    fun getOrderList(dateStart:String, dateEnd:String):LiveData<List<Order>>
    fun addOrder(order: Order)
}
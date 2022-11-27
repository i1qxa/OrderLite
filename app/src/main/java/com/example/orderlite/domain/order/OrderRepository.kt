package com.example.orderlite.domain.order

import androidx.lifecycle.LiveData

interface OrderRepository {

    fun getOrderList():LiveData<List<Order>>

    suspend fun addOrder(order: Order)

    suspend fun getOrder(id:Int):Order

    suspend fun deleteOrderRecordsWithOrder(id:Int)
}
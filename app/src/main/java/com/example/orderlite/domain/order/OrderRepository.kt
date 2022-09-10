package com.example.orderlite.domain.order

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun getOrderList(dateStart:String, dateEnd:String):LiveData<List<Order>>
    suspend fun addOrder(order: Order)
}
package com.example.orderlite.domain.order

import androidx.lifecycle.LiveData
import com.example.orderlite.domain.orderRecord.OrderRecord
import kotlinx.coroutines.flow.Flow

interface OrderRepository {

    fun getOrderList():LiveData<List<Order>>

    suspend fun addOrder(order: Order)

    fun getLastOrderRecord():LiveData<OrderRecord>
}
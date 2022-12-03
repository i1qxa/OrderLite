package com.example.orderlite.domain.order

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class GetOrderListUseCase(private val orderRepository: OrderRepository) {
    fun getOrderList():LiveData<List<Order>>{
        return orderRepository.getOrderList()
    }
}
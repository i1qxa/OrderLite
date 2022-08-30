package com.example.orderlite.domain.order

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class GetOrderListUseCase(private val orderRepository: OrderRepository) {
    fun getOrderList(dateStart:String, dateEnd:String):LiveData<List<Order>>{
        return orderRepository.getOrderList(dateStart,dateEnd)
    }
}
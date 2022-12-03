package com.example.orderlite.domain.order

class GetOrderUseCase(private val orderRepository: OrderRepository) {
    suspend fun getOrder(id:Int):Order{
        return orderRepository.getOrder(id)
    }
}
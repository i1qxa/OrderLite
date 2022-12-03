package com.example.orderlite.domain.order

class AddOrderUseCase(private val orderRepository: OrderRepository) {
    suspend fun addOrder(order: Order){
        orderRepository.addOrder(order)
    }
}
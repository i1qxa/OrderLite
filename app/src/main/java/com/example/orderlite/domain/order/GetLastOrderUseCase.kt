package com.example.orderlite.domain.order

class GetLastOrderUseCase(private val repository: OrderRepository) {
    suspend fun getLastOrder():Order{
        return repository.getLastOrder()
    }
}
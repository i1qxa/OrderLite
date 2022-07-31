package com.example.orderlite.domain.order

class GetOrderListUseCase(private val orderRepository: OrderRepository) {
    fun getOrderList(dateStart:String, dateEnd:String):List<Order>{
        return orderRepository.getOrderList(dateStart,dateEnd)
    }
}
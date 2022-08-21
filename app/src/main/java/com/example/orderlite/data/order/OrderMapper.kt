package com.example.orderlite.data.order

import com.example.orderlite.domain.order.Order

class OrderMapper {
    fun mapOrderToDBModel(order: Order):OrderDbModel{
        return OrderDbModel(
            orderId = order.id,
            orderDate = order.date
        )
    }
    fun mapDBModelToOrder(orderDbModel: OrderDbModel):Order{
        return Order(
            id = orderDbModel.orderId,
            date = orderDbModel.orderDate
        )
    }
    fun mapListOrderDBModelTOListOrder(list:List<OrderDbModel>) = list.map { mapDBModelToOrder(it) }
}
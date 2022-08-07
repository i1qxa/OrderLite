package com.example.orderlite.data.order

import com.example.orderlite.domain.order.Order

class OrderMapper {
    fun mapOrderToDBModel(order: Order):OrderDbModel{
        return OrderDbModel(
            id = order.id,
            date = order.date
        )
    }
    fun mapDBModelToOrder(orderDbModel: OrderDbModel):Order{
        return Order(
            id = orderDbModel.id,
            date = orderDbModel.date
        )
    }
    fun mapListOrderDBModelTOListOrder(list:List<OrderDbModel>) = list.map { mapDBModelToOrder(it) }
}
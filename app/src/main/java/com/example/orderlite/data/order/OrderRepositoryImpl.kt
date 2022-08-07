package com.example.orderlite.data.order

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.orderlite.data.AppDatabase
import com.example.orderlite.domain.order.Order
import com.example.orderlite.domain.order.OrderRepository

class OrderRepositoryImpl(application: Application):OrderRepository {

    private val orderDBModelDao = AppDatabase.getInstance(application).orderDbModelDao()
    private val mapper = OrderMapper()

    override fun getOrderList(dateStart: String, dateEnd: String): LiveData<List<Order>> {
        TODO()
    }

    override fun addOrder(order: Order) {
        orderDBModelDao.addOrder(mapper.mapOrderToDBModel(order))
    }

}
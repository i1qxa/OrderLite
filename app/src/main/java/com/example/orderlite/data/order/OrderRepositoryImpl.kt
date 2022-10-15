package com.example.orderlite.data.order

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.orderlite.data.AppDatabase
import com.example.orderlite.domain.order.Order
import com.example.orderlite.domain.order.OrderRepository
import kotlinx.coroutines.flow.Flow

class OrderRepositoryImpl(application: Application):OrderRepository {

    private val orderDBModelDao = AppDatabase.getInstance(application).orderDbModelDao()
    private val mapper = OrderMapper()

    override fun getOrderList(): LiveData<List<Order>> =
        Transformations.map(orderDBModelDao.getOrderList()){
            mapper.mapListOrderDBModelTOListOrder(it)
        }

    override suspend  fun addOrder(order: Order) {
        orderDBModelDao.addOrder(mapper.mapOrderToDBModel(order))
    }

}
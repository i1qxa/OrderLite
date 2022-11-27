package com.example.orderlite.presentation.order

import android.app.Application
import android.icu.text.SimpleDateFormat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.orderlite.data.order.OrderRepositoryImpl
import com.example.orderlite.domain.order.*
import kotlinx.coroutines.launch
import java.util.*

class ListOrderViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = OrderRepositoryImpl(application)
    private val getListOrderUseCase = GetOrderListUseCase(repository)
    private val addOrderUseCase = AddOrderUseCase(repository)
    private val deleteOrderRecordsWithOrderUseCas = DeleteOrderRecordsWithOrderUseCase(repository)
    val orderList = getListOrderUseCase.getOrderList()

    fun addOrder() {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        val newOrder = Order(0, currentDate)
        viewModelScope.launch {
            addOrderUseCase.addOrder(newOrder)
        }
    }

    fun deleteOrder(orderId: Int) {
        viewModelScope.launch {
            deleteOrderRecordsWithOrderUseCas.deleteOrderRecordsWithOrder(orderId)
        }
    }

}
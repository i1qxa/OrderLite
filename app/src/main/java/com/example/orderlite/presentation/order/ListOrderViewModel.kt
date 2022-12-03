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
    private val getLastOrderUseCase = GetLastOrderUseCase(repository)
    val orderList = getListOrderUseCase.getOrderList()
    private val _newOrderId = MutableLiveData<Int?>()
    val newOrderId:MutableLiveData<Int?>
    get() = _newOrderId

    fun addOrder() {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        val newOrder = Order(0, currentDate)
        viewModelScope.launch {
            addOrderUseCase.addOrder(newOrder)
            _newOrderId.value = getLastOrderUseCase.getLastOrder().id
        }
    }

    fun deleteOrder(orderId: Int) {
        viewModelScope.launch {
            deleteOrderRecordsWithOrderUseCas.deleteOrderRecordsWithOrder(orderId)
        }
    }

    fun clearNewOrderId(){
        _newOrderId.value = null
    }

}
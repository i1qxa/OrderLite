package com.example.orderlite.presentation.order

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.orderlite.data.order.OrderRepositoryImpl
import com.example.orderlite.domain.order.AddOrderUseCase
import com.example.orderlite.domain.order.GetOrderListUseCase
import com.example.orderlite.domain.order.Order
import kotlinx.coroutines.launch
import java.util.Calendar

class ListOrderViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = OrderRepositoryImpl(application)
    private val getListOrderUseCase = GetOrderListUseCase(repository)
    private val addOrderUseCase = AddOrderUseCase(repository)
    private val getOrder
    val orderList = getListOrderUseCase.getOrderList()
    var _newOrderId = MutableLiveData<Int>()
    val newOrderId:LiveData<Int>
    get() = _newOrderId



    fun addOrder() {
        val newOrder = Order(0, Calendar.getInstance().time.toString())
        viewModelScope.launch {
            addOrderUseCase.addOrder(newOrder)
        }
    }

}
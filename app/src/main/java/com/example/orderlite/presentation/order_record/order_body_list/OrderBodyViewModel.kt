package com.example.orderlite.presentation.order_record.order_body_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.orderlite.data.orderRecord.OrderRecordRepositoryImpl
import com.example.orderlite.domain.orderRecord.*
import kotlinx.coroutines.launch

class OrderBodyViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = OrderRecordRepositoryImpl(application)
    private val getOrderRecordWithProductItemAndUnitOMItemListUseCase =
        GetOrderRecordWithProductItemAndUnitOMItemListUseCase(repository)
    private val getOrderRecordListUseCase =
        GetOrderRecordListUseCase(repository)
    private val editOrderRecordUseCase = EditOrderRecordUseCase(repository)
    private val addListOrderRecordUseCase = AddListOrderRecordUseCase(repository)
    lateinit var orderRecordList: LiveData<List<OrderRecordWithProductItemAndUnitOMItem>>

    fun setOrderRecordJoinList(orderId: Int) {
        orderRecordList =
            getOrderRecordWithProductItemAndUnitOMItemListUseCase.getOrderRecordJoinList(orderId)
    }

    fun changeOrderRecordAmount(record: OrderRecord, amountStr: String) {
        val amount = amountStr.toDouble()
        if (amount != record.amount) {
            val newOrderRecord = record.copy()
            newOrderRecord.amount = amount
            viewModelScope.launch {
                editOrderRecordUseCase.editOrderRecord(newOrderRecord)
            }
        }
    }

    fun changeOrderRecordPrice(record: OrderRecord, priceStr: String) {
        val price = priceStr.toDouble()
        if (price != record.price) {
            val newOrderRecord = record.copy()
            newOrderRecord.price = price
            viewModelScope.launch {
                editOrderRecordUseCase.editOrderRecord(newOrderRecord)
            }
        }
    }

    fun addRecordsFromAnotherOrder(orderId: Int){
        val additionalOrder = getOrderRecordListUseCase.getOrderRecordList(orderId)
        viewModelScope.launch{
            addListOrderRecordUseCase.addListOrderRecord(additionalOrder.value!!)
        }
    }
}
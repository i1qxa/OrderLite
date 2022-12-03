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
    private val editOrderRecordUseCase = EditOrderRecordUseCase(repository)
    private val addListOrderRecordUseCase = AddListOrderRecordUseCase(repository)
    private val deleteOrderRecordUseCase = DeleteOrderRecordUseCase(repository)
    lateinit var orderRecordList: LiveData<List<OrderRecordWithProductItemAndUnitOMItem>>

    fun setOrderRecordJoinList(orderId: Int) {
        orderRecordList =
            getOrderRecordWithProductItemAndUnitOMItemListUseCase.getOrderRecordJoinList(orderId)
        }

    fun changeOrderRecordAmount(record: OrderRecord, amountStr: String) {
        val amount = if (amountStr.isNotEmpty()) amountStr.toDouble() else 0.0
        if (amount != record.amount) {
            val newOrderRecord = record.copy()
            newOrderRecord.amount = amount
            viewModelScope.launch {
                editOrderRecordUseCase.editOrderRecord(newOrderRecord)
            }
        }
    }

    fun changeOrderRecordPrice(record: OrderRecord, priceStr: String) {
        val price = if (priceStr.isNotEmpty()) priceStr.toInt() else 0
        if (price != record.price) {
            val newOrderRecord = record.copy()
            newOrderRecord.price = price
            viewModelScope.launch {
                editOrderRecordUseCase.editOrderRecord(newOrderRecord)
            }
        }
    }

    fun addRecordsFromAnotherOrder(baseOrderId: Int, additionalOrderId: Int) {
        viewModelScope.launch {
            addListOrderRecordUseCase.addListOrderRecord(baseOrderId, additionalOrderId)
        }
    }

    fun deleteOrderRecord(orderId:Int, productItemId:Int){
        viewModelScope.launch {
            deleteOrderRecordUseCase.deleteOrderRecord(orderId, productItemId)
        }
    }
}
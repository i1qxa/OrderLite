package com.example.orderlite.presentation.order_record.order_body_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.orderlite.data.orderRecord.OrderRecordRepositoryImpl
import com.example.orderlite.domain.orderRecord.EditOrderRecordUseCase
import com.example.orderlite.domain.orderRecord.GetOrderRecordWithProductItemAndUnitOMItemListUseCase
import com.example.orderlite.domain.orderRecord.OrderRecord
import com.example.orderlite.domain.orderRecord.OrderRecordWithProductItemAndUnitOMItem
import kotlinx.coroutines.launch

class OrderBodyViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = OrderRecordRepositoryImpl(application)
    private val getOrderRecordWithProductItemAndUnitOMItemListUseCase =
        GetOrderRecordWithProductItemAndUnitOMItemListUseCase(repository)
    private val editOrderRecordUseCase = EditOrderRecordUseCase(repository)
    lateinit var orderRecordList: LiveData<List<OrderRecordWithProductItemAndUnitOMItem>>


    fun setOrderRecordJoinList(orderId: Int) {
        orderRecordList =
            getOrderRecordWithProductItemAndUnitOMItemListUseCase.getOrderRecordJoinList(orderId)
    }

    fun changeOrderRecordAmount(orderRecord: OrderRecord) {
        viewModelScope.launch {
            editOrderRecordUseCase.editOrderRecord(orderRecord)
        }
    }

}
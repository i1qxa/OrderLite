package com.example.orderlite.presentation.order_record.order_body_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.orderlite.data.orderRecord.OrderRecordRepositoryImpl
import com.example.orderlite.domain.orderRecord.GetOrderRecordWithProductItemAndUnitOMItemListUseCase
import com.example.orderlite.domain.orderRecord.OrderRecordWithProductItemAndUnitOMItem

class OrderBodyViewModel(application: Application):AndroidViewModel(application) {
    private val repository = OrderRecordRepositoryImpl(application)
    private val getOrderRecordWithProductItemAndUnitOMItemListUseCase = GetOrderRecordWithProductItemAndUnitOMItemListUseCase(repository)
    lateinit var orderRecordList:LiveData<List<OrderRecordWithProductItemAndUnitOMItem>>



    fun setOrderRecordJoinList(orderId:Int){
        orderRecordList = getOrderRecordWithProductItemAndUnitOMItemListUseCase.
        getOrderRecordJoinList(orderId)
    }

}
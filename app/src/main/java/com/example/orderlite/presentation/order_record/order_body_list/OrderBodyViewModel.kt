package com.example.orderlite.presentation.order_record.order_body_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.orderlite.data.orderRecord.OrderRecordRepositoryImpl
import com.example.orderlite.domain.orderRecord.GetOrderRecordListUseCase

class OrderBodyViewModel(application: Application):AndroidViewModel(application) {
    private val repository = OrderRecordRepositoryImpl(application)
    private val getOrderRecordListUseCase = GetOrderRecordListUseCase(repository)
    private var orderId:Int? = null

    val orderRecordList = getOrderRecordListUseCase.
    getOrderRecordList(orderId?:throw RuntimeException("OrderId = null"))

    fun setOrderId(id:Int){
        if (id>0) orderId = id
        else throw RuntimeException("Incorrect OrderId :$id")
    }
}
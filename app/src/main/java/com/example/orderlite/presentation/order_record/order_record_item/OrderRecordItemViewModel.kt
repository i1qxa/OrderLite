package com.example.orderlite.presentation.order_record.order_record_item

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.orderlite.data.orderRecord.OrderRecordRepositoryImpl
import com.example.orderlite.data.productItem.ProductItemRepositoryImpl
import com.example.orderlite.data.unitsOM.UnitsOMRepositoryImpl
import com.example.orderlite.domain.orderRecord.AddOrderRecordUseCase
import com.example.orderlite.domain.orderRecord.OrderRecord
import com.example.orderlite.domain.productItem.GetProductItemUseCase
import com.example.orderlite.domain.productItem.ProductItem
import com.example.orderlite.domain.unitsOfMeasurement.GetListUnitsOMUseCase
import com.example.orderlite.domain.unitsOfMeasurement.GetUnitsOMUseCase
import com.example.orderlite.domain.unitsOfMeasurement.UnitsOfMItem
import kotlinx.coroutines.launch

class OrderRecordItemViewModel(application: Application) : AndroidViewModel(application) {
    private var repositoryOrderRecord = OrderRecordRepositoryImpl(application)
    private var repositoryProductItem = ProductItemRepositoryImpl(application)
    private var repositoryUnitOM = UnitsOMRepositoryImpl(application)

    private var addOrderRecordUseCase = AddOrderRecordUseCase(repositoryOrderRecord)
    private var getProductItemUseCase = GetProductItemUseCase(repositoryProductItem)
    private var getUnitOMItemUseCase = GetUnitsOMUseCase(repositoryUnitOM)
    private var getListUnitOMUseCase = GetListUnitsOMUseCase(repositoryUnitOM)

    private var _errorInputAmount = MutableLiveData<Boolean>()
    val errorInputAmount:LiveData<Boolean>
    get() = _errorInputAmount
    private var _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen:LiveData<Unit>
    get() = _shouldCloseScreen
    private var orderAmount: Double? = null
    private var orderId: Int? = null
    private lateinit var orderRecordItem: OrderRecord

    private lateinit var _productItem: MutableLiveData<ProductItem>
    val productItem: LiveData<ProductItem>
        get() = _productItem

    private lateinit var _unitOMItem: MutableLiveData<UnitsOfMItem>
    val unitOMItem: LiveData<UnitsOfMItem>
        get() = _unitOMItem

    var listUnitOM = getListUnitOMUseCase.getListUnitsOM()

    fun addOrderRecord() {
        prepareOrderRecord()
        viewModelScope.launch {
            addOrderRecordUseCase.addOrderRecord(orderRecordItem)
            finishWork()
        }
    }

    fun setOrderId(id: Int?) {
        orderId = id
    }

    private fun prepareOrderRecord() {
        if (parseParams()) {
            orderRecordItem =
                OrderRecord(0, orderId!!, productItem.value!!.id, unitOMItem.value!!.id,
                    0.0, orderAmount!!)
        }
    }

    fun parseAmount(amount:String):Boolean{
        orderAmount = amount.toDouble()
        var ans = true
        if (orderAmount!!<=0.0){
            _errorInputAmount.value = true
            ans = false
        }
        return ans
    }

    private fun parseParams(): Boolean {
        val ans = orderId != null && orderAmount != null && productItem.value != null
        return if (ans) {
            true
        } else {
            throw RuntimeException("Incorrect params for OrderRecord")
        }
    }

    fun getProductItem(productItemId: Int) {
        viewModelScope.launch {
            _productItem.value = getProductItemUseCase.getProductItem(productItemId)
        }
    }

    fun getUnitOMItem(unitOMId: Int) {
        viewModelScope.launch {
            _unitOMItem.value = getUnitOMItemUseCase.getUnitsOM(unitOMId)
        }
    }

    fun finishWork(){
        _shouldCloseScreen.value = Unit
    }

    fun resetErrorInput(){
        _errorInputAmount.value = false
    }
}
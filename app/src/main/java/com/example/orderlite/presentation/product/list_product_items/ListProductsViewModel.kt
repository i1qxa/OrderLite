package com.example.orderlite.presentation.product.list_product_items

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
import com.example.orderlite.domain.productItem.GetProductItemListUseCase
import com.example.orderlite.domain.unitsOfMeasurement.GetUnitsOMUseCase
import kotlinx.coroutines.launch

class ListProductsViewModel(application: Application) : AndroidViewModel(application) {
    private val repositoryProduct = ProductItemRepositoryImpl(application)
    private val repositoryUnitOM = UnitsOMRepositoryImpl(application)
    private val getUnitOMItemUseCase = GetUnitsOMUseCase(repositoryUnitOM)
    private val getProductItemListUseCase = GetProductItemListUseCase(repositoryProduct)
    private val repositoryOrderRecord = OrderRecordRepositoryImpl(application)
    private val addOrderRecordUseCase = AddOrderRecordUseCase(repositoryOrderRecord)
    val productItemList = getProductItemListUseCase.getProductItemList()
    private var _unitOMName = MutableLiveData<String>()
    val unitOMName:LiveData<String>
    get() = _unitOMName

    var orderId: Int? = null
    var productItemId: Int? = null
    var unitOMId: Int? = null
    var orderRecordAmount: Double = 0.0

    fun addOrderRecord() {
        viewModelScope.launch {
            val newOrderRecord = prepareOrderRecord()
            addOrderRecordUseCase.addOrderRecord(newOrderRecord)
        }
    }

    fun getUnitOMName(unitOMId: Int) {
        viewModelScope.launch {
            _unitOMName.value = getUnitOMItemUseCase.getUnitsOM(unitOMId).name
        }
    }

    fun setupOrderId(id: Int?) {
        orderId = id ?: throw RuntimeException("Order Id = null")
    }

    fun setupProductItemId(id: Int?) {
        productItemId = id ?: throw RuntimeException("Product Item Id = null")
    }

    fun setupUnitOMId(id: Int?) {
        unitOMId = id ?: throw RuntimeException("UnitOM ID = null")
    }

    fun setupOrderRecordAmount(amount: Double?) {
        orderRecordAmount = amount ?: 0.0
    }

    fun clearParams() {
        productItemId = null
        unitOMId = null
        orderRecordAmount = 0.0
    }

    private fun validateParams(): Boolean {
        return orderId != null && productItemId != null && unitOMId != null
    }

    private fun prepareOrderRecord(): OrderRecord {
        if (validateParams()) {
            return OrderRecord(0, orderId!!, productItemId!!, unitOMId!!, 0, orderRecordAmount)
        } else throw RuntimeException("One or many params for new OrderRecord = null")
    }


}
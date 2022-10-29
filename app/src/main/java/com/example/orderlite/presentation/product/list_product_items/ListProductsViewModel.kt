package com.example.orderlite.presentation.product.list_product_items

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.orderlite.data.orderRecord.OrderRecordRepositoryImpl
import com.example.orderlite.data.productItem.ProductItemRepositoryImpl
import com.example.orderlite.domain.orderRecord.AddOrderRecordUseCase
import com.example.orderlite.domain.orderRecord.OrderRecord
import com.example.orderlite.domain.productItem.GetProductItemListUseCase
import com.example.orderlite.domain.productItem.ProductItem
import com.example.orderlite.domain.unitsOfMeasurement.UnitsOfMItem

class ListProductsViewModel(application: Application):AndroidViewModel(application) {
    private val repository = ProductItemRepositoryImpl(application)
    private val getProductItemListUseCase = GetProductItemListUseCase(repository)
    private val repositoryOrderRecord = OrderRecordRepositoryImpl(application)
    private val addOrderRecordUseCase = AddOrderRecordUseCase(repositoryOrderRecord)
    val productItemList = getProductItemListUseCase.getProductItemList()

    suspend fun addOrderRecord(orderId:Int, productItemId: Int, unitOMItemId: Int, price:Double, amount:Double){
        val newOrderRecord = OrderRecord(0,orderId,productItemId,unitOMItemId,price,amount)
        addOrderRecordUseCase.addOrderRecord(newOrderRecord)
    }
}
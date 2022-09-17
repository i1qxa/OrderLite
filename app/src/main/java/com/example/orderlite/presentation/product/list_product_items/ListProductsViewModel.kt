package com.example.orderlite.presentation.product.list_product_items

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.orderlite.data.productItem.ProductItemRepositoryImpl
import com.example.orderlite.domain.productItem.GetProductItemListUseCase

class ListProductsViewModel(application: Application):AndroidViewModel(application) {
    private val repository = ProductItemRepositoryImpl(application)
    private val getProductItemListUseCase = GetProductItemListUseCase(repository)
    val productItemList = getProductItemListUseCase.getProductItemList()
}
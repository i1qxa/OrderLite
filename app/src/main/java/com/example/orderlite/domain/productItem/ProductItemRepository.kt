package com.example.orderlite.domain.productItem

import androidx.lifecycle.LiveData

interface ProductItemRepository {
    fun addProductItem(productItem:ProductItem)

    fun deleteProductItem(productItemId:Int)

    fun editProductItemUseCase(productItem: ProductItem)

    fun getProductItemList():LiveData<List<ProductItem>>

    fun getProductItem(productItemId:Int):ProductItem
}
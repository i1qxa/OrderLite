package com.example.orderlite.domain.productItem

import androidx.lifecycle.LiveData

interface ProductItemRepository {
    suspend fun addProductItem(productItem:ProductItem)

    suspend fun deleteProductItem(productItemId:Int)

    suspend fun editProductItem(productItem: ProductItem)

    fun getProductItemList():LiveData<List<ProductItem>>

    suspend fun getProductItem(productItemId:Int):ProductItem

    suspend fun getProductItemWithUnitOM(productItemId: Int):ProductItemWithUnitOMItem

    fun getListProductItemWithUnitOMItem(listProductItemId:List<Int>):LiveData<List<ProductItemWithUnitOMItem>>
}
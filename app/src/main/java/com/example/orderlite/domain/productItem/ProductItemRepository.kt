package com.example.orderlite.domain.productItem

interface ProductItemRepository {
    fun addProductItem(productItem:ProductItem)

    fun deleteProductItem(productItemId:Int)

    fun editProductItemUseCase(productItem: ProductItem)

    fun getProductItemList():List<ProductItem>

    fun getProductItem(productItemId:Int):ProductItem
}
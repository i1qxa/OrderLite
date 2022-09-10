package com.example.orderlite.domain.productItem

class AddProductItemUseCase (private val productItemRepository: ProductItemRepository){
    suspend fun addProductItem(productItem:ProductItem){
        productItemRepository.addProductItem(productItem)
    }
}
package com.example.orderlite.domain.productItem

class AddProductItemUseCase (private val productItemRepository: ProductItemRepository){
    fun addProductItem(productItem:ProductItem){
        productItemRepository.addProductItem(productItem)
    }
}
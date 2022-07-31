package com.example.orderlite.domain.productItem

class EditProductItemUseCase (private val productItemRepository: ProductItemRepository){
    fun editProductItemUseCase(productItem: ProductItem){
        productItemRepository.editProductItemUseCase(productItem)
    }
}
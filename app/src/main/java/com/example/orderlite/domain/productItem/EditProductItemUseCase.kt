package com.example.orderlite.domain.productItem

class EditProductItemUseCase (private val productItemRepository: ProductItemRepository){
    suspend fun editProductItemUseCase(productItem: ProductItem){
        productItemRepository.editProductItemUseCase(productItem)
    }
}
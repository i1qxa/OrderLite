package com.example.orderlite.domain.productItem

class EditProductItemUseCase (private val productItemRepository: ProductItemRepository){
    suspend fun editProductItem(productItem: ProductItem){
        productItemRepository.editProductItem(productItem)
    }
}
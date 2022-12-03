package com.example.orderlite.domain.productItem

class GetProductItemWithUnitOMItemUseCase(private val repository: ProductItemRepository) {
    suspend fun getProductItemWithUnitOM(productItemId: Int):ProductItemWithUnitOMItem{
        return repository.getProductItemWithUnitOM(productItemId)
    }
}
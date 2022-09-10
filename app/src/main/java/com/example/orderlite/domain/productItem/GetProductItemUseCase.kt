package com.example.orderlite.domain.productItem

class GetProductItemUseCase (private val productItemRepository: ProductItemRepository){
    suspend fun getProductItem(productItemId:Int):ProductItem{
         return productItemRepository.getProductItem(productItemId)
    }
}
package com.example.orderlite.domain.productItem

class GetProductItemUseCase (private val productItemRepository: ProductItemRepository){
    fun getProductItem(productItemId:Int):ProductItem{
         return productItemRepository.getProductItem(productItemId)
    }
}
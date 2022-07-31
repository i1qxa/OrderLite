package com.example.orderlite.domain.productItem

class DeleteProductItemUseCase (private val productItemRepository: ProductItemRepository){
    fun deleteProductItem(productItemId:Int){
        productItemRepository.deleteProductItem(productItemId)
    }
}
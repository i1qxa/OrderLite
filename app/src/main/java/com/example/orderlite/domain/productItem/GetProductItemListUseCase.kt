package com.example.orderlite.domain.productItem

class GetProductItemListUseCase (private val productItemRepository: ProductItemRepository){
    fun getProductItemList():List<ProductItem>{
        return productItemRepository.getProductItemList()
    }
}
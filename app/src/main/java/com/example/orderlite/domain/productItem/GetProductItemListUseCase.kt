package com.example.orderlite.domain.productItem

import androidx.lifecycle.LiveData

class GetProductItemListUseCase (private val productItemRepository: ProductItemRepository){
    fun getProductItemList():LiveData<List<ProductItem>>{
        return productItemRepository.getProductItemList()
    }
}
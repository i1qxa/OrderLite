package com.example.orderlite.domain.productItem

import androidx.lifecycle.LiveData

class GetListProductItemWithUnitOMItemUseCase(private val repository: ProductItemRepository) {
    fun getListProductItemWithUnitOMItem(listProductItemId:List<Int>):LiveData<List<ProductItemWithUnitOMItem>>{
        return repository.getListProductItemWithUnitOMItem(listProductItemId)
    }
}
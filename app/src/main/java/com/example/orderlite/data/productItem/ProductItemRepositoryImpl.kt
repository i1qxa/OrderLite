package com.example.orderlite.data.productItem

import android.app.Application
import com.example.orderlite.data.AppDatabase
import com.example.orderlite.domain.productItem.ProductItem
import com.example.orderlite.domain.productItem.ProductItemRepository

class ProductItemRepositoryImpl(application: Application):ProductItemRepository {
    private val productItemDao = AppDatabase.getInstance(application).productItemDbModelDao()
    private val mapper = ProductItemMapper()

    override fun addProductItem(productItem: ProductItem) {
        productItemDao.addProductItem(mapper.mapProductItemToDb(productItem))
    }

    override fun deleteProductItem(productItemId: Int) {
        productItemDao.deleteProductItem(productItemId)
    }

    override fun editProductItemUseCase(productItem: ProductItem) {
        productItemDao.addProductItem(mapper.mapProductItemToDb(productItem))
    }

    override fun getProductItemList(): List<ProductItem> {
        TODO("Not yet implemented")
    }

    override fun getProductItem(productItemId: Int): ProductItem {
        val productItemDB = productItemDao.getProductItem(productItemId)
        return mapper.mapDBToProductItem(productItemDB)
    }
}
package com.example.orderlite.data.productItem

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.orderlite.data.AppDatabase
import com.example.orderlite.data.unitsOM.UnitsOMMapper
import com.example.orderlite.domain.productItem.ProductItem
import com.example.orderlite.domain.productItem.ProductItemRepository
import com.example.orderlite.domain.productItem.ProductItemWithUnitOMItem

class ProductItemRepositoryImpl(application: Application):ProductItemRepository {
    private val productItemDao = AppDatabase.getInstance(application).productItemDbModelDao()
    private val mapper = ProductItemMapper()
    private val mapperUnitOM = UnitsOMMapper()

    override suspend fun addProductItem(productItem: ProductItem) {
        productItemDao.addProductItem(mapper.mapProductItemToDb(productItem))
    }

    override suspend fun deleteProductItem(productItemId: Int) {
        productItemDao.deleteProductItem(productItemId)
    }

    override suspend fun editProductItem(productItem: ProductItem) {
        productItemDao.addProductItem(mapper.mapProductItemToDb(productItem))
    }

    override fun getProductItemList(): LiveData<List<ProductItem>> = Transformations
        .map(productItemDao.getProductItemList()){
            mapper.mapListDBToListProductItem(it)
        }

    override suspend fun getProductItem(productItemId: Int): ProductItem {
        val productItemDB = productItemDao.getProductItem(productItemId)
        return mapper.mapDBToProductItem(productItemDB)
    }

    override suspend fun getProductItemWithUnitOM(productItemId: Int): ProductItemWithUnitOMItem {
        val productItemWithUnitOMItemDBModel = productItemDao.getProductItemWithUnitOMItem(productItemId)
        return ProductItemWithUnitOMItem(
            mapper.mapDBToProductItem(productItemWithUnitOMItemDBModel.productItem),
            mapperUnitOM.mapDBToUnitOM(productItemWithUnitOMItemDBModel.unitOMItem)
        )
    }

    override fun getListProductItemWithUnitOMItem(listProductItemId: List<Int>): LiveData<List<ProductItemWithUnitOMItem>> {
        TODO("Not yet implemented")
    }
}
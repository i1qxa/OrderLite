package com.example.orderlite.data.productItem

import com.example.orderlite.domain.productItem.ProductItem

class ProductItemMapper {
    fun mapProductItemToDb(productItem: ProductItem):ProductItemDbModel{
        return ProductItemDbModel(
            productItemId = productItem.id,
            defaultUnitId = productItem.defaultUnitId,
            productItemName = productItem.name
        )
    }
    fun mapDBToProductItem(productItemDB: ProductItemDbModel):ProductItem{
        return ProductItem(
            id = productItemDB.productItemId,
            defaultUnitId = productItemDB.defaultUnitId,
            name = productItemDB.productItemName
        )
    }
    fun mapListDBToListProductItem(list:List<ProductItemDbModel>) = list.map { mapDBToProductItem(it) }
}
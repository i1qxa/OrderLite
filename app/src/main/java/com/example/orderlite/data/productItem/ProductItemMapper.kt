package com.example.orderlite.data.productItem

import com.example.orderlite.domain.productItem.ProductItem

class ProductItemMapper {
    fun mapProductItemToDb(productItem: ProductItem):ProductItemDbModel{
        return ProductItemDbModel(
            id = productItem.id,
            defaultUnitId = productItem.defaultUnitId,
            name = productItem.name
        )
    }
    fun mapDBToProductItem(productItemDB: ProductItemDbModel):ProductItem{
        return ProductItem(
            id = productItemDB.id,
            defaultUnitId = productItemDB.defaultUnitId,
            name = productItemDB.name
        )
    }
    fun mapListDBToListProductItem(list:List<ProductItemDbModel>) = list.map { mapDBToProductItem(it) }
}
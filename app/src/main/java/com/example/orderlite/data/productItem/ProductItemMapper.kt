package com.example.orderlite.data.productItem

import com.example.orderlite.domain.productItem.ProductItem

class ProductItemMapper {
    fun mapProductItemToDb(productItem: ProductItem):ProductItemDbModel{
        return ProductItemDbModel(
            productItemId = productItem.id,
            defaultUnitId = productItem.defaultUnitOM,
            productItemName = productItem.name
        )
    }
    fun mapDBToProductItem(productItemDB: ProductItemDbModel):ProductItem{
        return ProductItem(
            id = productItemDB.productItemId,
            defaultUnitOM = productItemDB.defaultUnitId,
            name = productItemDB.productItemName
        )
    }
    fun mapListDBToListProductItem(list:List<ProductItemDbModel>) = list.map { mapDBToProductItem(it) }
}
package com.example.orderlite.domain.orderRecord

import com.example.orderlite.domain.productItem.ProductItem
import com.example.orderlite.domain.unitsOfMeasurement.UnitsOfMItem

data class OrderRecordWithProductItemAndUnitOMItem(
    val orderRecord: OrderRecord,
    val productItem: ProductItem,
    val unitOMItem:UnitsOfMItem
)
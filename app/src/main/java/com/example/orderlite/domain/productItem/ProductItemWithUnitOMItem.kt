package com.example.orderlite.domain.productItem

import com.example.orderlite.domain.unitsOfMeasurement.UnitsOfMItem

data class ProductItemWithUnitOMItem(
    val productItem:ProductItem,
    val unitOMItem:UnitsOfMItem
)
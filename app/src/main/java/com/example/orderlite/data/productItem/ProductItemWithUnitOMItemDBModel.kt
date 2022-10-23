package com.example.orderlite.data.productItem

import androidx.room.Embedded
import androidx.room.Relation
import com.example.orderlite.data.unitsOM.UnitsOMDbModel

data class ProductItemWithUnitOMItemDBModel(
    @Embedded
    val productItem:ProductItemDbModel,
    @Relation(
        parentColumn = "default_unit_id",
        entityColumn = "unit_id"

    )
    val unitOMItem:UnitsOMDbModel
)
package com.example.orderlite.data.orderRecord

import androidx.room.Embedded
import androidx.room.Relation
import com.example.orderlite.data.productItem.ProductItemDbModel
import com.example.orderlite.data.unitsOM.UnitsOMDbModel

data class OrderRecordWithProductItemAndUnitOMItemDBModel(
 @Embedded
 val orderRecord:OrderRecordDbModel,
 @Relation(
         parentColumn = "product_id",
         entityColumn = "product_item_id"
 )
 val productItemDB:ProductItemDbModel,
 @Relation(
     parentColumn = "unit_id",
     entityColumn = "unit_id"
 )
 val unitOMDB:UnitsOMDbModel,
)
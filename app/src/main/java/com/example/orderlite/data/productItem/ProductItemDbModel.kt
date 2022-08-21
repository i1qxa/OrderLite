package com.example.orderlite.data.productItem

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.orderlite.data.unitsOM.UnitsOMDbModel

@Entity(
    tableName = "product_item",
    foreignKeys = [
        ForeignKey(
            entity = UnitsOMDbModel::class,
            parentColumns = ["unit_id"],
            childColumns = ["default_unit_id"]
        )
    ]

)
data class ProductItemDbModel (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_item_id")
    val productItemId:Int,
    @ColumnInfo(name = "default_unit_id")
    val defaultUnitId:Int,
    @ColumnInfo(name = "product_item_name")
    val productItemName:String
)
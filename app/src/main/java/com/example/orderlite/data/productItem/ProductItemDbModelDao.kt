package com.example.orderlite.data.productItem

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductItemDbModelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProductItem(productItem: ProductItemDbModel)
    @Query("DELETE FROM product_item WHERE id = :productItemId")
    fun deleteProductItem(productItemId:Int)
    @Query("SELECT * FROM product_item")
    fun getProductItemList():LiveData<List<ProductItemDbModel>>
    @Query("SELECT * FROM product_item WHERE id = :productItemId")
    fun getProductItem(productItemId:Int): ProductItemDbModel
}
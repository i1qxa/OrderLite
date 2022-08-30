package com.example.orderlite.data.productItem

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductItemDbModelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProductItem(productItem: ProductItemDbModel)
    @Query("DELETE FROM product_item WHERE product_item_id = :productItemId")
    suspend fun deleteProductItem(productItemId:Int)
    @Query("SELECT * FROM product_item")
    fun getProductItemList():LiveData<List<ProductItemDbModel>>
    @Query("SELECT * FROM product_item WHERE product_item_id = :productItemId")
    suspend fun getProductItem(productItemId:Int): ProductItemDbModel
}
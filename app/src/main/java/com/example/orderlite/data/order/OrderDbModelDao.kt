package com.example.orderlite.data.order

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OrderDbModelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrder(order: OrderDbModel)
    @Query("SELECT * FROM `order`")
    fun getOrderList():LiveData<List<OrderDbModel>>
    @Query("SELECT * FROM `order` WHERE order_id =:id ")
    suspend fun getOrder(id:Int):OrderDbModel

}
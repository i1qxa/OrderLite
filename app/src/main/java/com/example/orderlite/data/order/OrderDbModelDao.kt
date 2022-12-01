package com.example.orderlite.data.order

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface OrderDbModelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrder(order: OrderDbModel)
    @Query("SELECT * FROM `order` ORDER BY order_date DESC")
    fun getOrderList():LiveData<List<OrderDbModel>>
    @Query("SELECT * FROM `order` WHERE order_id =:id ")
    suspend fun getOrder(id:Int):OrderDbModel
    @Query("SELECT * FROM `order` ORDER BY order_date DESC LIMIT 1")
    suspend fun getLastOrder():OrderDbModel
    @Query("DELETE FROM order_record WHERE order_id =:id")
    suspend fun deleteOrderRecords(id:Int)
    @Query("DELETE FROM `order` WHERE order_id =:id")
    suspend fun deleteOrder(id:Int)
    @Transaction
    suspend fun deleteOrderRecordsWithOrder(orderId:Int){
        deleteOrderRecords(orderId)
        deleteOrder(orderId)
    }
}
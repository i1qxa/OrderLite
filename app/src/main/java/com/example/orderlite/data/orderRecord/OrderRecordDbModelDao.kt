package com.example.orderlite.data.orderRecord

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface OrderRecordDbModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrderRecord(orderRecordDbModel: OrderRecordDbModel)
    @Query("DELETE FROM order_record WHERE order_id = :id")
    suspend fun deleteOrderRecord(id:Int)
    @Query("SELECT * FROM order_record WHERE order_id = :orderId")
    fun getOrderRecordList(orderId:Int):LiveData<List<OrderRecordDbModel>>
    @Query("SELECT * FROM order_record WHERE order_id = :orderRecordId")
    suspend fun getOrderRecord(orderRecordId:Int): OrderRecordDbModel
}
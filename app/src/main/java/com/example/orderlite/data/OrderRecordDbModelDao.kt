package com.example.orderlite.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface OrderRecordDbModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addOrderRecord(orderRecordDbModel: OrderRecordDbModel)
    @Query("DELETE FROM order_record WHERE id = :id")
    fun deleteOrderRecord(id:Int)
    @Query("SELECT * FROM order_record WHERE orderId = :orderId")
    fun getOrderRecordList(orderId:Int):LiveData<List<OrderRecordDbModel>>
    @Query("SELECT * FROM order_record WHERE id = :orderRecordId")
    fun getOrderRecord(orderRecordId:Int):OrderRecordDbModel
}
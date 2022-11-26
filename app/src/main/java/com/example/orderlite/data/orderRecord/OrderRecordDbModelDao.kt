package com.example.orderlite.data.orderRecord

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.orderlite.domain.orderRecord.OrderRecord

@Dao
interface OrderRecordDbModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrderRecord(orderRecordDbModel: OrderRecordDbModel)

    @Query("DELETE FROM order_record WHERE order_id = :id")
    suspend fun deleteOrderRecord(id:Int)

    @Query("SELECT * FROM order_record WHERE order_id = :orderId")
    fun getOrderRecordLDList(orderId:Int):LiveData<List<OrderRecordDbModel>>

    @Query("SELECT * FROM order_record WHERE order_id = :orderId")
    suspend fun getOrderRecordList(orderId: Int):List<OrderRecordDbModel>

    @Query("SELECT * FROM order_record WHERE order_id = :orderId AND product_id = :productItemId LIMIT 1")
    suspend fun getOrderRecord(orderId:Int, productItemId:Int): OrderRecordDbModel

    @Query("SELECT * FROM order_record WHERE order_id = :orderId")
    fun getOrderRecordListWithProductItemAndUnitOMItemDB(orderId: Int):
            LiveData<List<OrderRecordWithProductItemAndUnitOMItemDBModel>>

}
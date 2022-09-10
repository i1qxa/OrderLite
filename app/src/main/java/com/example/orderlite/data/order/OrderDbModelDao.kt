package com.example.orderlite.data.order

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDbModelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrder(order: OrderDbModel)
    @Query("SELECT * FROM `order` WHERE order_date > :dateStart and order_date < :dateEnd")
    fun getOrderList(dateStart:String,dateEnd:String):LiveData<List<OrderDbModel>>

}
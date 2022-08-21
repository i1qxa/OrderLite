package com.example.orderlite.data.order

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OrderDbModelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addOrder(order: OrderDbModel)
    @Query("SELECT * FROM `order` WHERE orderDate > :dateStart and orderDate < :dateEnd")
    fun getOrderList(dateStart:String,dateEnd:String):LiveData<List<OrderDbModel>>

}
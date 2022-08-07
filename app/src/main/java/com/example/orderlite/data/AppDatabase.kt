package com.example.orderlite.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.orderlite.data.order.OrderDbModel
import com.example.orderlite.data.order.OrderDbModelDao
import com.example.orderlite.data.orderRecord.OrderRecordDbModel
import com.example.orderlite.data.orderRecord.OrderRecordDbModelDao
import com.example.orderlite.data.productItem.ProductItemDbModel
import com.example.orderlite.data.productItem.ProductItemDbModelDao
import com.example.orderlite.data.unitsOM.UnitsOMDbModel
import com.example.orderlite.data.unitsOM.UnitsOMDbModelDao

@Database(
    entities = [
        OrderDbModel::class,
        OrderRecordDbModel::class,
        ProductItemDbModel::class,
        UnitsOMDbModel::class
               ],
version = 1, exportSchema = false)
abstract class AppDatabase:RoomDatabase() {

    abstract fun orderDbModelDao(): OrderDbModelDao
    abstract fun orderRecordDbModelDao(): OrderRecordDbModelDao
    abstract fun productItemDbModelDao(): ProductItemDbModelDao
    abstract fun unitsOMDBModelDao(): UnitsOMDbModelDao

    companion object{
        private var INSTANCE:AppDatabase?=null
        private val LOCK = Any()
        private const val DB_NAME = "order_db"
        fun getInstance(application: Application):AppDatabase{
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK){
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }

    }
}
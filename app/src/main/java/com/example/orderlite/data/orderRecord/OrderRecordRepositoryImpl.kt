package com.example.orderlite.data.orderRecord

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.orderlite.data.AppDatabase
import com.example.orderlite.domain.orderRecord.OrderRecord
import com.example.orderlite.domain.orderRecord.OrderRecordWithProductItemAndUnitOMItem
import com.example.orderlite.domain.orderRecord.OrderRecordRepository

class OrderRecordRepositoryImpl(application: Application):OrderRecordRepository {

    private val orderRecordDao = AppDatabase.getInstance(application).orderRecordDbModelDao()
    private val mapper = OrderRecordMapper()

    override suspend  fun addOrderRecord(orderRecord: OrderRecord) {
        orderRecordDao.addOrderRecord(mapper.mapOrderRecordToDB(orderRecord))
    }

    override suspend fun deleteOrderRecord(orderRecordId: Int) {
        orderRecordDao.deleteOrderRecord(orderRecordId)
    }

    override suspend fun editOrderRecord(orderRecord: OrderRecord) {
        orderRecordDao.addOrderRecord(mapper.mapOrderRecordToDB(orderRecord))
    }

    override fun getOrderRecordList(orderId: Int): LiveData<List<OrderRecord>> = Transformations
        .map(orderRecordDao.getOrderRecordList(orderId)){
            mapper.mapListDBToOrderRecord(it)
        }

    override suspend fun getOrderRecord(orderRecordId: Int): OrderRecord {
        val orderRecordDB = orderRecordDao.getOrderRecord(orderRecordId)
        return mapper.mapDBToOrderRecord(orderRecordDB)
    }

    override fun getOrderRecordJoinList(orderId: Int): LiveData<List<OrderRecordWithProductItemAndUnitOMItem>> =
        Transformations.map(orderRecordDao.getOrderRecordListWithProductItemAndUnitOMItemDB(orderId)){
            mapper.mapListDBToListOrderRecordWithProductItemAndUnitOMItem(it)
        }
}
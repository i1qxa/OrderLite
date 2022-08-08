package com.example.orderlite.data.orderRecord

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.orderlite.data.AppDatabase
import com.example.orderlite.domain.orderRecord.OrderRecord
import com.example.orderlite.domain.orderRecord.OrderRecordRepository

class OrderRecordRepositoryImpl(application: Application):OrderRecordRepository {

    private val orderRecordDao = AppDatabase.getInstance(application).orderRecordDbModelDao()
    private val mapper = OrderRecordMapper()

    override fun addOrderRecord(orderRecord: OrderRecord) {
        orderRecordDao.addOrderRecord(mapper.mapOrderRecordToDB(orderRecord))
    }

    override fun deleteOrderRecord(orderRecordId: Int) {
        orderRecordDao.deleteOrderRecord(orderRecordId)
    }

    override fun editOrderRecord(orderRecord: OrderRecord) {
        orderRecordDao.addOrderRecord(mapper.mapOrderRecordToDB(orderRecord))
    }

    override fun getOrderRecordList(orderId: Int): LiveData<List<OrderRecord>> = Transformations
        .map(orderRecordDao.getOrderRecordList(orderId)){
            mapper.mapListDBToOrderRecord(it)
        }

    override fun getOrderRecord(orderRecordId: Int): OrderRecord {
        val orderRecordDB = orderRecordDao.getOrderRecord(orderRecordId)
        return mapper.mapDBToOrderRecord(orderRecordDB)
    }
}
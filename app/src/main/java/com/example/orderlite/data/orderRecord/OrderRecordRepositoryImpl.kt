package com.example.orderlite.data.orderRecord

import android.app.Application
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

    override fun getOrderRecordList(orderId: Int): List<OrderRecord> {
        TODO("Not yet implemented")
    }

    override fun getOrderRecord(orderRecordId: Int): OrderRecord {
        val orderRecordDB = orderRecordDao.getOrderRecord(orderRecordId)
        return mapper.mapDBToOrderRecord(orderRecordDB)
    }
}
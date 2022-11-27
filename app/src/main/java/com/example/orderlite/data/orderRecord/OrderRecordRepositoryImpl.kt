package com.example.orderlite.data.orderRecord

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.orderlite.data.AppDatabase
import com.example.orderlite.domain.orderRecord.OrderRecord
import com.example.orderlite.domain.orderRecord.OrderRecordWithProductItemAndUnitOMItem
import com.example.orderlite.domain.orderRecord.OrderRecordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class OrderRecordRepositoryImpl(application: Application) : OrderRecordRepository {

    private val orderRecordDao = AppDatabase.getInstance(application).orderRecordDbModelDao()
    private val mapper = OrderRecordMapper()

    override suspend fun addOrderRecord(orderRecord: OrderRecord) {
        GlobalScope.launch(Dispatchers.IO) {
            val existingRecord =
                orderRecordDao.getOrderRecord(orderRecord.orderId, orderRecord.productId)
            if (existingRecord != null) {
                val totalAmount = existingRecord.amount + orderRecord.amount
                orderRecordDao.addOrderRecord(existingRecord.copy(amount = totalAmount))
            } else {
                orderRecordDao.addOrderRecord(mapper.mapOrderRecordToDB(orderRecord))
            }
        }
    }

    override suspend fun deleteOrderRecord(orderRecordId: Int, productItemId: Int) {
        orderRecordDao.deleteOrderRecord(orderRecordId, productItemId)
    }

    override suspend fun editOrderRecord(orderRecord: OrderRecord) {
        orderRecordDao.addOrderRecord(mapper.mapOrderRecordToDB(orderRecord))
    }

    override fun getOrderRecordLDList(orderId: Int): LiveData<List<OrderRecord>> = Transformations
        .map(orderRecordDao.getOrderRecordLDList(orderId)) {
            mapper.mapListDBToOrderRecord(it)
        }

    override suspend fun getOrderRecordList(orderId: Int): List<OrderRecord> {
        val result = mutableListOf<OrderRecord>()
        orderRecordDao.getOrderRecordList(orderId).forEach {
            result.add(mapper.mapDBToOrderRecord(it))
        }
        return result
    }

    override suspend fun getOrderRecord(orderId: Int, productItemId: Int): OrderRecord {
        val orderRecordDB = orderRecordDao.getOrderRecord(orderId, productItemId)
        return mapper.mapDBToOrderRecord(orderRecordDB)
    }

    override fun getOrderRecordJoinList(orderId: Int): LiveData<List<OrderRecordWithProductItemAndUnitOMItem>> =
        Transformations.map(orderRecordDao.getOrderRecordListWithProductItemAndUnitOMItemDB(orderId)) {
            mapper.mapListDBToListOrderRecordWithProductItemAndUnitOMItem(it)
        }

    override suspend fun addListOrderRecord(baseOrderId: Int,additionalOrderId: Int) {
        val additionalList = getOrderRecordList(additionalOrderId)
        additionalList.forEach {
            val newRecord = it.copy(id = 0, orderId = baseOrderId)
            addOrderRecord(newRecord)
        }
    }
}
package com.example.orderlite.data.orderRecord

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.orderlite.data.AppDatabase
import com.example.orderlite.domain.orderRecord.OrderRecord
import com.example.orderlite.domain.orderRecord.OrderRecordWithProductItemAndUnitOMItem
import com.example.orderlite.domain.orderRecord.OrderRecordRepository

class OrderRecordRepositoryImpl(application: Application):OrderRecordRepository {

    private var total:Double = 0.0
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

    override suspend fun getOrderRecord(orderId: Int, productItemId:Int): OrderRecord {
        val orderRecordDB = orderRecordDao.getOrderRecord(orderId, productItemId)
        return mapper.mapDBToOrderRecord(orderRecordDB)
    }

    override fun getOrderRecordJoinList(orderId: Int): LiveData<List<OrderRecordWithProductItemAndUnitOMItem>> =
        Transformations.map(orderRecordDao.getOrderRecordListWithProductItemAndUnitOMItemDB(orderId)){
            mapper.mapListDBToListOrderRecordWithProductItemAndUnitOMItem(it)
        }

    override suspend fun addListOrderRecord(listOrderRecord: List<OrderRecord>) {
        listOrderRecord.forEach{
            val oldOrderRecordDBM = orderRecordDao.getOrderRecord(it.orderId, it.productId)
            total = if (oldOrderRecordDBM!=null) oldOrderRecordDBM.amount + it.amount
            else
                it.amount
            val newOrderRecordDBM = mapper.mapOrderRecordToDB(it).copy(amount = total)
            orderRecordDao.addOrderRecord(newOrderRecordDBM)
        }
    }
}
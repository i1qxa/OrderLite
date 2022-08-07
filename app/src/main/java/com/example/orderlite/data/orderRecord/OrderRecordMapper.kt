package com.example.orderlite.data.orderRecord

import com.example.orderlite.domain.orderRecord.OrderRecord

class OrderRecordMapper {
    fun mapOrderRecordToDB(orderRecord: OrderRecord):OrderRecordDbModel{
        return OrderRecordDbModel(
            id = orderRecord.id,
            orderId =  orderRecord.orderId,
            productId = orderRecord.productId,
            unitId = orderRecord.unitId,
            price = orderRecord.price,
            amount = orderRecord.amount
        )
    }
    fun mapDBToOrderRecord(orderRecordDbModel: OrderRecordDbModel):OrderRecord{
        return OrderRecord(
            id = orderRecordDbModel.id,
            orderId =  orderRecordDbModel.orderId,
            productId = orderRecordDbModel.productId,
            unitId = orderRecordDbModel.unitId,
            price = orderRecordDbModel.price,
            amount = orderRecordDbModel.amount
        )
    }
    fun mapListDBToOrderRecord(list:List<OrderRecordDbModel>)=list.map { mapDBToOrderRecord(it) }
}
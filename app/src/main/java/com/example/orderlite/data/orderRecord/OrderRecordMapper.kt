package com.example.orderlite.data.orderRecord

import com.example.orderlite.data.productItem.ProductItemMapper
import com.example.orderlite.data.unitsOM.UnitsOMMapper
import com.example.orderlite.domain.orderRecord.OrderRecord
import com.example.orderlite.domain.orderRecord.OrderRecordWithProductItemAndUnitOMItem

class OrderRecordMapper {

    private val mapperProductItem = ProductItemMapper()
    private val mapperUnitOMMapper = UnitsOMMapper()

    fun mapOrderRecordToDB(orderRecord: OrderRecord):OrderRecordDbModel{
        return OrderRecordDbModel(
            orderRecordId = orderRecord.id,
            orderId =  orderRecord.orderId,
            productId = orderRecord.productId,
            unitId = orderRecord.unitId,
            price = orderRecord.price,
            amount = orderRecord.amount
        )
    }
    fun mapDBToOrderRecord(orderRecordDbModel: OrderRecordDbModel):OrderRecord{
        return OrderRecord(
            id = orderRecordDbModel.orderRecordId,
            orderId =  orderRecordDbModel.orderId,
            productId = orderRecordDbModel.productId,
            unitId = orderRecordDbModel.unitId,
            price = orderRecordDbModel.price,
            amount = orderRecordDbModel.amount
        )
    }

    fun mapDBToOrderRecordWithProductItemAndUnitOMItem(
        OrderRecordWithProductItemAndUnitOMItemDBModel: OrderRecordWithProductItemAndUnitOMItemDBModel
    ):OrderRecordWithProductItemAndUnitOMItem{
        return OrderRecordWithProductItemAndUnitOMItem(
            mapDBToOrderRecord(OrderRecordWithProductItemAndUnitOMItemDBModel.orderRecord),
            mapperProductItem.mapDBToProductItem(OrderRecordWithProductItemAndUnitOMItemDBModel.productItemDB),
            mapperUnitOMMapper.mapDBToUnitOM(OrderRecordWithProductItemAndUnitOMItemDBModel.unitOMDB)
        )
    }

    fun mapListDBToOrderRecord(list:List<OrderRecordDbModel>)=list.map { mapDBToOrderRecord(it) }

    fun mapListDBToListOrderRecordWithProductItemAndUnitOMItem(
        list:List<OrderRecordWithProductItemAndUnitOMItemDBModel>) =
        list.map { mapDBToOrderRecordWithProductItemAndUnitOMItem(it) }
}
package com.example.orderlite.presentation.order_record.order_body_list

import androidx.recyclerview.widget.DiffUtil
import com.example.orderlite.domain.orderRecord.OrderRecordWithProductItemAndUnitOMItem

class OrderBodyRVDiffCallBack():DiffUtil.ItemCallback<OrderRecordWithProductItemAndUnitOMItem>() {
    override fun areItemsTheSame(
        oldItem: OrderRecordWithProductItemAndUnitOMItem,
        newItem: OrderRecordWithProductItemAndUnitOMItem,
    ): Boolean {
        return oldItem.productItem.id == newItem.productItem.id
    }

    override fun areContentsTheSame(
        oldItem: OrderRecordWithProductItemAndUnitOMItem,
        newItem: OrderRecordWithProductItemAndUnitOMItem,
    ): Boolean {
        return oldItem == newItem
    }
}
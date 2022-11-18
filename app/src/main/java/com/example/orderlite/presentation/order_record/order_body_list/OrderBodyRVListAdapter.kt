package com.example.orderlite.presentation.order_record.order_body_list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.ListAdapter
import com.example.orderlite.R
import com.example.orderlite.domain.orderRecord.OrderRecordWithProductItemAndUnitOMItem

class OrderBodyRVListAdapter:ListAdapter
<OrderRecordWithProductItemAndUnitOMItem,OrderBodyRVViewHolder>
    (OrderBodyRVDiffCallBack()) {
    var onItemClickListener: ((OrderRecordWithProductItemAndUnitOMItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderBodyRVViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return OrderBodyRVViewHolder(
            layoutInflater.inflate(
                R.layout.order_body_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OrderBodyRVViewHolder, position: Int) {
        val item = getItem(position)
        with(holder){
            tvOIOrderNumber.text = position.toString()
            tvOIName.text = item.productItem.name
            tvOIUnitOM.text = item.unitOMItem.name
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(item)
        }

    }
}
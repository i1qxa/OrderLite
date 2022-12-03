package com.example.orderlite.presentation.order

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.orderlite.R
import com.example.orderlite.domain.order.Order

class ListOrderRVListAdapter : androidx.recyclerview.widget.ListAdapter
<Order, ListOrderViewHolder>(ListOrderDiffCallBack()) {

    var orderItemClickListener: ((Order) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListOrderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ListOrderViewHolder(
            layoutInflater.inflate(
                R.layout.order_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListOrderViewHolder, position: Int) {
        val orderItem = getItem(position)
        with(holder) {
            tvOrderId.text = orderItem.id.toString()
            tvOrderDate.text = orderItem.date
            itemView.setOnClickListener {
                orderItemClickListener?.invoke(orderItem)
            }
        }

    }
}
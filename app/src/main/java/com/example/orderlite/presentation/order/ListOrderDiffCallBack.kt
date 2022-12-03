package com.example.orderlite.presentation.order

import androidx.recyclerview.widget.DiffUtil
import com.example.orderlite.domain.order.Order

class ListOrderDiffCallBack():DiffUtil.ItemCallback<Order>() {
    override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem == newItem
    }
}
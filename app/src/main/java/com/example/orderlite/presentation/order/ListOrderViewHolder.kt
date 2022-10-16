package com.example.orderlite.presentation.order

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.orderlite.R

class ListOrderViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
    val tvOrderId = itemView.findViewById<TextView>(R.id.tvOrderId)
    val tvOrderDate = itemView.findViewById<TextView>(R.id.tvOrderDate)
}
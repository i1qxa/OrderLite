package com.example.orderlite.presentation.order_record.order_body_list

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.orderlite.R

class OrderBodyRVViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
    val tvOIOrderNumber = itemView.findViewById<TextView>(R.id.tvOIOrderNumber)
    val tvOIName = itemView.findViewById<TextView>(R.id.tvOIName)
    val tvOIUnitOM = itemView.findViewById<TextView>(R.id.tvOIUnitOM)
    val etOIAmount = itemView.findViewById<TextView>(R.id.etOIAmount)
    val tvOISum = itemView.findViewById<TextView>(R.id.tvOISum)
}
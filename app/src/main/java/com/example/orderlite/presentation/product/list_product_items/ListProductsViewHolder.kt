package com.example.orderlite.presentation.product.list_product_items

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.orderlite.R

class ListProductsViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
    val tvProductName = itemView.findViewById<TextView>(R.id.tvProductName)
}
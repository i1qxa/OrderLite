package com.example.orderlite.presentation.product.list_product_items

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.orderlite.R
import com.example.orderlite.domain.productItem.ProductItem

class ListProductsRVListAdapter:androidx.recyclerview.widget.ListAdapter<
        ProductItem,ListProductsViewHolder>
    (ListProductsDiffCallback()) {

    var productItemClickListener: ((ProductItem) -> Unit)?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListProductsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ListProductsViewHolder(
            layoutInflater.inflate(
                R.layout.unit_o_m_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListProductsViewHolder, position: Int) {
        val productItem = getItem(position)
        holder.tvProductName.text = productItem.name
        holder.itemView.setOnClickListener{
            productItemClickListener?.invoke(productItem)
        }
    }
}
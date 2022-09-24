package com.example.orderlite.presentation.product.list_product_items

import androidx.recyclerview.widget.DiffUtil
import com.example.orderlite.domain.productItem.ProductItem

class ListProductsDiffCallback():DiffUtil.ItemCallback<ProductItem>(){
    override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
        return oldItem == newItem
    }
}
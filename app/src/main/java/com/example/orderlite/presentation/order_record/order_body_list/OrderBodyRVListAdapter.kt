package com.example.orderlite.presentation.order_record.order_body_list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.ListAdapter
import com.example.orderlite.R
import com.example.orderlite.domain.orderRecord.OrderRecord
import com.example.orderlite.domain.orderRecord.OrderRecordWithProductItemAndUnitOMItem

class OrderBodyRVListAdapter : ListAdapter
<OrderRecordWithProductItemAndUnitOMItem, OrderBodyRVViewHolder>
    (OrderBodyRVDiffCallBack()) {
    var onAmountChangeFinished: ((OrderRecord, amountStr: String) -> Unit)? = null
    var onPriceChangeFinished: ((OrderRecord, priceStr:String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderBodyRVViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layout = when(viewType){
            EMPTY_PRICE -> R.layout.order_body_item
            CHANGED_PRICE -> R.layout.order_body_item_changed
            else -> throw RuntimeException("Unknown viewType")
        }
        return OrderBodyRVViewHolder(
            layoutInflater.inflate(
                layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OrderBodyRVViewHolder, position: Int) {
        val item = getItem(position)
        with(holder) {
            tvOIName.text = item.productItem.name
            tvOIUnitOM.text = item.unitOMItem.shortName
            etOIAmount.setText(item.orderRecord.amount.toString())
            etOIPrice.setText(item.orderRecord.price.toString())
            tvOISum.text = item.orderRecord.sum.toString()
        }
        holder.etOIAmount.setOnFocusChangeListener { v, hasFocus ->
            val amountStr = v.findViewById<EditText>(R.id.etOIAmount).text.toString()
            if (!hasFocus) {
                onAmountChangeFinished?.invoke(item.orderRecord, amountStr)
            }
        }
        holder.etOIPrice.setOnFocusChangeListener { v, hasFocus ->
            val priceStr = v.findViewById<EditText>(R.id.etOIPrice).text.toString()
            if (!hasFocus) {
                onPriceChangeFinished?.invoke(item.orderRecord, priceStr)
                }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.orderRecord.price==0) EMPTY_PRICE else CHANGED_PRICE
    }

    companion object{
        const val EMPTY_PRICE = 1
        const val CHANGED_PRICE = 2
    }
}
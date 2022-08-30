package com.example.orderlite.domain.unitsOfMeasurement

import androidx.recyclerview.widget.DiffUtil

class UnitOMListDiffCallBack(
    ):DiffUtil.ItemCallback<UnitsOfMItem>() {
    override fun areItemsTheSame(oldItem: UnitsOfMItem, newItem: UnitsOfMItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UnitsOfMItem, newItem: UnitsOfMItem): Boolean {
        return oldItem == newItem
    }
}
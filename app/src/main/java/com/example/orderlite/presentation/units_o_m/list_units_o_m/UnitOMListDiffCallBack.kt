package com.example.orderlite.presentation.units_o_m.list_units_o_m

import androidx.recyclerview.widget.DiffUtil
import com.example.orderlite.domain.unitsOfMeasurement.UnitsOfMItem

class UnitOMListDiffCallBack(
    ):DiffUtil.ItemCallback<UnitsOfMItem>() {
    override fun areItemsTheSame(oldItem: UnitsOfMItem, newItem: UnitsOfMItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UnitsOfMItem, newItem: UnitsOfMItem): Boolean {
        return oldItem == newItem
    }
}
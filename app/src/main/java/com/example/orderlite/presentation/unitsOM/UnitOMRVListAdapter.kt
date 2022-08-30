package com.example.orderlite.presentation.unitsOM

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.orderlite.R
import com.example.orderlite.domain.unitsOfMeasurement.UnitOMListDiffCallBack
import com.example.orderlite.domain.unitsOfMeasurement.UnitsOfMItem

class UnitOMRVListAdapter:androidx.recyclerview.widget.ListAdapter<UnitsOfMItem,UnitOMViewHolder>(UnitOMListDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnitOMViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UnitOMViewHolder(
            layoutInflater.inflate(
                R.layout.unit_o_m_item,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: UnitOMViewHolder, position: Int) {
        val unitOMItem = getItem(position)
        with(holder){
            tvId.text = unitOMItem.id.toString()
            tvName.text = unitOMItem.name
            tvShortName.text = unitOMItem.shortName
        }
    }
}
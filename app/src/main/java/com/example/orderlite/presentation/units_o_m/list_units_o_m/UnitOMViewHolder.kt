package com.example.orderlite.presentation.units_o_m.list_units_o_m

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.orderlite.R

class UnitOMViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val tvId = itemView.findViewById<TextView>(R.id.tvId)
    val tvName = itemView.findViewById<TextView>(R.id.tvName)
    val tvShortName = itemView.findViewById<TextView>(R.id.tvShortName)
}
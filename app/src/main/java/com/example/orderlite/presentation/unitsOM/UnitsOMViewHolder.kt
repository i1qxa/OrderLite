package com.example.orderlite.presentation.unitsOM

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.orderlite.R

class UnitsOMViewHolder(val view: View):RecyclerView.ViewHolder(view) {
    val tvId = view.findViewById<TextView>(R.id.tvId)
    val tvName = view.findViewById<TextView>(R.id.tvName)
    val tvShortName = view.findViewById<TextView>(R.id.tvShortName)
}
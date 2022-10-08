package com.example.orderlite.presentation.product.product_item

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.orderlite.R
import com.example.orderlite.domain.unitsOfMeasurement.UnitsOfMItem

class MySpinnerAdapter(context:Context, unitOMList:List<UnitsOfMItem>):
    ArrayAdapter<UnitsOfMItem>(context,0,unitOMList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position,convertView,parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position,convertView,parent)
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup):View{
        val unitOM = getItem(position)
        val view = LayoutInflater.from(context).inflate(R.layout.spinner_item,parent,false)
        view.findViewById<TextView>(R.id.tvUnitOM).text = unitOM?.name.toString()
        return view
    }

}
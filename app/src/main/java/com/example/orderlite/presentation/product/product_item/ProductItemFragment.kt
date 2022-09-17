package com.example.orderlite.presentation.product.product_item

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.orderlite.R
import com.example.orderlite.presentation.units_o_m.unit_o_m.SCREEN_MODE
import com.example.orderlite.presentation.units_o_m.unit_o_m.ITEM_ID

const val FRAGMENT_NAME_PRODUCT_ITEM = "Product item"

class ProductItemFragment : Fragment() {
    private var screenMode: String? = null
    private var productId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            screenMode = it.getString(SCREEN_MODE)
            productId = it.getInt(ITEM_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_item, container, false)
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(mode: String, itemId: Int) =
            ProductItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, mode)
                    putInt(ITEM_ID, itemId)
                }
            }
    }
}
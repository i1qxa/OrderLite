package com.example.orderlite.presentation.order_record.order_record_item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.orderlite.databinding.FragmentAddOrderRecordBinding

private const val PRODUCT_ITEM_ID = "product_item_id"
private const val UNIT_O_M_ITEM_ID = "unit_o_m_item_id"

class AddOrderRecordFragment : Fragment() {
    private lateinit var viewModel: OrderRecordItemViewModel
    private var productItemId: Int? = null
    private var unitOMItemId: Int? = null
    private lateinit var binding: FragmentAddOrderRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productItemId = it.getInt(PRODUCT_ITEM_ID)
            unitOMItemId = it.getInt(UNIT_O_M_ITEM_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddOrderRecordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[OrderRecordItemViewModel::class.java]
    }

    companion object {
        @JvmStatic
        fun newInstance(productItemId: Int, unitOMItemId: Int) =
            AddOrderRecordFragment().apply {
                arguments = Bundle().apply {
                    putInt(PRODUCT_ITEM_ID, productItemId)
                    putInt(UNIT_O_M_ITEM_ID, unitOMItemId)
                }
            }
    }
}
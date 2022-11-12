package com.example.orderlite.presentation.order_record.order_body_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.orderlite.R
import com.example.orderlite.databinding.FragmentListOrderBinding
import com.example.orderlite.presentation.FragmentNameInstaller
import com.example.orderlite.presentation.order.FRAGMENT_NAME_ORDER_LIST
import com.example.orderlite.presentation.product.list_product_items.ORDER_ID

const val FRAGMENT_NAME_ORDER_BODY = "Order"


class OrderBodyFragment : Fragment() {

    private lateinit var binding: FragmentListOrderBinding
    private lateinit var viewModel: OrderBodyViewModel
    private var orderId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentListOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentNameInstaller.setName(FRAGMENT_NAME_ORDER_BODY)
        parseParams()
        viewModel = ViewModelProvider(this)[OrderBodyViewModel::class.java]
        prepareViewModel()
    }

    private fun prepareViewModel() {
            viewModel.setOrderId(orderId)
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(ORDER_ID)) throw RuntimeException("Param OrderId is absent")
        orderId = args.getInt(ORDER_ID)
    }

    companion object {
        @JvmStatic
        fun newInstance(orderId: Int) = OrderBodyFragment().apply {
            arguments = Bundle().apply {
                putInt(ORDER_ID, orderId)
            }
        }
    }
}
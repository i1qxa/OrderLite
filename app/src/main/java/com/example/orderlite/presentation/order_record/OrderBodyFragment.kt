package com.example.orderlite.presentation.order_record

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.orderlite.R
import com.example.orderlite.databinding.FragmentListOrderBinding
import com.example.orderlite.presentation.FragmentNameInstaller
import com.example.orderlite.presentation.order.FRAGMENT_NAME_ORDER_LIST

const val FRAGMENT_NAME_ORDER_BODY = "Order"
const val ORDER_ID = "OrderId"

class OrderBodyFragment : Fragment() {

    private lateinit var binding:FragmentListOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentListOrderBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentNameInstaller.setName(FRAGMENT_NAME_ORDER_LIST)

    }

    companion object {
        @JvmStatic
        fun newInstance(orderId:Int) = OrderBodyFragment().apply {
            arguments = Bundle().apply {
                putInt(ORDER_ID,orderId)
            }
        }
    }
}
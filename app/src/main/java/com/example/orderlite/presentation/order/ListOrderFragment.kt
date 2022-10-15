package com.example.orderlite.presentation.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.orderlite.R
import com.example.orderlite.presentation.FragmentNameInstaller
import com.example.orderlite.presentation.order_record.OrderBodyFragment

const val FRAGMENT_NAME_ORDER_LIST = "OrderList"

class ListOrderFragment : Fragment() {

    private lateinit var fragmentNameInstaller:FragmentNameInstaller
    private lateinit var viewModel: ListOrderViewModel
    private var orderId:Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_list_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNameInstaller = FragmentNameInstaller
        fragmentNameInstaller.setName(FRAGMENT_NAME_ORDER_LIST)
        viewModel = ViewModelProvider(this)[ListOrderViewModel::class.java]
    }


    private fun launchNewFragment(fragment: OrderBodyFragment){
        parentFragmentManager.apply {
            beginTransaction()
                .replace(R.id.mainContainerView,fragment)
                .addToBackStack(null)
                .commit()
        }

        }


    companion object {
        @JvmStatic
        fun newInstance() = ListOrderFragment()
    }
}
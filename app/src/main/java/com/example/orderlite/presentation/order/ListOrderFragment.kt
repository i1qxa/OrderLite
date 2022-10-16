package com.example.orderlite.presentation.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.orderlite.R
import com.example.orderlite.databinding.FragmentListOrderBinding
import com.example.orderlite.presentation.FragmentNameInstaller
import com.example.orderlite.presentation.order_record.OrderBodyFragment
import kotlinx.coroutines.flow.combine

const val FRAGMENT_NAME_ORDER_LIST = "OrderList"

class ListOrderFragment : Fragment() {

    private lateinit var binding:FragmentListOrderBinding
    private lateinit var fragmentNameInstaller:FragmentNameInstaller
    private lateinit var viewModel: ListOrderViewModel
    private var orderId:Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentListOrderBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNameInstaller = FragmentNameInstaller
        fragmentNameInstaller.setName(FRAGMENT_NAME_ORDER_LIST)
        viewModel = ViewModelProvider(this)[ListOrderViewModel::class.java]
        setupOnClickListener()

    }

    private fun setupOnClickListener(){
        binding.fabAddOrder.setOnClickListener {
            viewModel.addOrder()
        }
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
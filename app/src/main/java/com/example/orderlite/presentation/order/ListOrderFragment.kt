package com.example.orderlite.presentation.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.orderlite.R
import com.example.orderlite.databinding.FragmentListOrderBinding
import com.example.orderlite.presentation.FragmentNameInstaller
import com.example.orderlite.presentation.order_record.order_body_list.OrderBodyFragment
import com.example.orderlite.presentation.order_record.order_body_list.REQUEST_ORDER_ID
import com.example.orderlite.presentation.product.list_product_items.MODE_LIST_VIEW
import com.example.orderlite.presentation.product.list_product_items.MODE_MULTI_CHOOSE
import com.example.orderlite.presentation.product.list_product_items.ORDER_ID
import com.example.orderlite.presentation.units_o_m.unit_o_m.SCREEN_MODE

class ListOrderFragment : Fragment() {

    private var screenMode: String? = null
    private lateinit var binding: FragmentListOrderBinding
    private lateinit var fragmentNameInstaller: FragmentNameInstaller
    private lateinit var viewModel: ListOrderViewModel
    private lateinit var rvAdapter: ListOrderRVListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentListOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNameInstaller = FragmentNameInstaller
        fragmentNameInstaller.setName(R.string.order_list)
        viewModel = ViewModelProvider(this)[ListOrderViewModel::class.java]
        setupOnClickListener()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupOnClickListener() {
        binding.fabAddOrder.setOnClickListener {
            viewModel.addOrder()
        }
    }

    private fun setupRecyclerView() {
        rvAdapter = ListOrderRVListAdapter()
        with(binding.rvOrderList) {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
        }
        rvAdapter.orderItemClickListener = {
            if (screenMode == MODE_LIST_VIEW) launchOrderBodyFragment(OrderBodyFragment.newInstance(
                it.id))
            else {
                parentFragmentManager.setFragmentResult(
                    REQUEST_ORDER_ID,
                    bundleOf(ORDER_ID to it.id)
                )
                parentFragmentManager.popBackStack()
            }
        }
        rvAdapter.orderItemLongClickListener = {
            viewModel.deleteOrder(it.id)
        }
    }

    private fun observeViewModel() {
        viewModel.orderList.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
        }
    }

    private fun launchOrderBodyFragment(fragment: OrderBodyFragment) {
        parentFragmentManager.apply {
            beginTransaction()
                .replace(R.id.mainContainerView, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) throw RuntimeException("Param ScreenMode is absent")
        screenMode = args.getString(SCREEN_MODE)
        if (screenMode != MODE_LIST_VIEW && screenMode != MODE_MULTI_CHOOSE)
            throw RuntimeException("Unknown ScreenMode: $screenMode")
    }

    companion object {
        @JvmStatic
        fun newInstance(screenMode: String) = ListOrderFragment().apply {
            arguments = Bundle().apply {
                putString(SCREEN_MODE, screenMode)
            }
        }

    }
}
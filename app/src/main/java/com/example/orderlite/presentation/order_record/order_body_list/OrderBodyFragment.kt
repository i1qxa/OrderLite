package com.example.orderlite.presentation.order_record.order_body_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.orderlite.R
import com.example.orderlite.databinding.FragmentOrderBodyBinding
import com.example.orderlite.domain.orderRecord.OrderRecord
import com.example.orderlite.presentation.FragmentNameInstaller
import com.example.orderlite.presentation.order.ListOrderFragment
import com.example.orderlite.presentation.product.list_product_items.ListProductsFragment
import com.example.orderlite.presentation.product.list_product_items.MODE_MULTI_CHOOSE
import com.example.orderlite.presentation.product.list_product_items.ORDER_ID

const val REQUEST_ORDER_ID = "request_order_id"

class OrderBodyFragment : Fragment() {

    private lateinit var binding: FragmentOrderBodyBinding
    private lateinit var viewModel: OrderBodyViewModel
    private var orderId: Int = -1
    private lateinit var rvAdapter: OrderBodyRVListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentOrderBodyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentNameInstaller.setName(R.string.order)
        viewModel = ViewModelProvider(this)[OrderBodyViewModel::class.java]
        setupFragmentResultListener()
        prepareViewModel()
        setupFabClickListener()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupFragmentResultListener() {
        parentFragmentManager.setFragmentResultListener(REQUEST_ORDER_ID, viewLifecycleOwner)
        { _, result ->
            val additionalOrderId = result.getInt(ORDER_ID)
            viewModel.addRecordsFromAnotherOrder(orderId, additionalOrderId)
        }
    }

    private fun setupRecyclerView() {
        setupRvAdapter()
        with(binding.rvListProductItems) {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
        }
    }

    private fun setupRvAdapter() {
        rvAdapter = OrderBodyRVListAdapter()
        rvAdapter.onItemClickListener = {
            Log.d("OrderBody", it.productItem.name)
        }
        rvAdapter.onAmountChangeFinished = { record: OrderRecord, amountString: String ->
            viewModel.changeOrderRecordAmount(record, amountString)
        }
        rvAdapter.onPriceChangeFinished = { record: OrderRecord, priceStr: String ->
            viewModel.changeOrderRecordPrice(record, priceStr)
        }
    }

    private fun observeViewModel() {
        viewModel.orderRecordList.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
        }
    }

    private fun setupFabClickListener() {
        binding.fabChooseProducts.setOnClickListener {
            val fragment = ListProductsFragment.newInstance(MODE_MULTI_CHOOSE, orderId)
            launchProductListFragment(fragment)
        }
        binding.fabAddOrderBody.setOnClickListener {
            launchOrderListFragment(ListOrderFragment.newInstance(MODE_MULTI_CHOOSE))
        }
    }

    private fun launchOrderListFragment(fragment: ListOrderFragment) {
        parentFragmentManager.apply {
            beginTransaction()
                .replace(R.id.mainContainerView, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun launchProductListFragment(fragment: ListProductsFragment) {
        parentFragmentManager.apply {
            beginTransaction()
                .replace(R.id.mainContainerView, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun prepareViewModel() {
        viewModel.setOrderRecordJoinList(orderId)
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
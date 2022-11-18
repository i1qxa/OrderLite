package com.example.orderlite.presentation.order_record.order_body_list

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.orderlite.R
import com.example.orderlite.databinding.FragmentListOrderBinding
import com.example.orderlite.databinding.FragmentOrderBodyBinding
import com.example.orderlite.presentation.FragmentNameInstaller
import com.example.orderlite.presentation.order.FRAGMENT_NAME_ORDER_LIST
import com.example.orderlite.presentation.product.list_product_items.ListProductsFragment
import com.example.orderlite.presentation.product.list_product_items.MODE_MULTI_CHOOSE
import com.example.orderlite.presentation.product.list_product_items.ORDER_ID

const val FRAGMENT_NAME_ORDER_BODY = "Order"


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
        FragmentNameInstaller.setName(FRAGMENT_NAME_ORDER_BODY)
        viewModel = ViewModelProvider(this)[OrderBodyViewModel::class.java]
        prepareViewModel()
        setupFabClickListener()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        rvAdapter = OrderBodyRVListAdapter()
        rvAdapter.onItemClickListener = {
            Log.d("OrderBody", it.productItem.name)
        }
        with(binding.rvListProductItems) {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
        }
    }

    private fun observeViewModel(){
        viewModel.orderRecordList.observe(viewLifecycleOwner){
            rvAdapter.submitList(it)

        }
    }

    private fun setupFabClickListener() {
        binding.fabChooseProducts.setOnClickListener {
            val fragment = ListProductsFragment.newInstance(MODE_MULTI_CHOOSE, orderId)
            launchProductListFragment(fragment)
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
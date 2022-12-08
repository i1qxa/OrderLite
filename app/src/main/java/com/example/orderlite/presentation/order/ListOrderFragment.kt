package com.example.orderlite.presentation.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
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

fun Fragment.launchNewFragment(fragment:Fragment) = run {
    parentFragmentManager.apply {
        beginTransaction()
            .replace(R.id.mainContainerView, fragment)
            .addToBackStack(null)
            .commit()
    }
}

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
            when(screenMode){
                MODE_LIST_VIEW -> this.launchNewFragment(OrderBodyFragment.newInstance(it.id))
                MODE_MULTI_CHOOSE -> {
                    parentFragmentManager.setFragmentResult(
                        REQUEST_ORDER_ID,
                        bundleOf(ORDER_ID to it.id)
                    )
                    parentFragmentManager.popBackStack()
                }
            }
        }
        setupItemSwipeListener(binding.rvOrderList)
    }

    private fun setupItemSwipeListener(rvOrderList:RecyclerView){
        val callback = object :
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = rvAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteOrder(item.id)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvOrderList)
    }

    private fun observeViewModel() {
        viewModel.orderList.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
        }
        viewModel.newOrderId.observe(viewLifecycleOwner){
            if (it!=null) {
                viewModel.clearNewOrderId()
                this.launchNewFragment(OrderBodyFragment.newInstance(it))
            }
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
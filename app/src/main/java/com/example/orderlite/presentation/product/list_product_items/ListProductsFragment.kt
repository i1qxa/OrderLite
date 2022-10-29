package com.example.orderlite.presentation.product.list_product_items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.orderlite.R
import com.example.orderlite.databinding.FragmentListProductsBinding
import com.example.orderlite.presentation.FragmentNameInstaller
import com.example.orderlite.presentation.product.product_item.ProductItemFragment
import com.example.orderlite.presentation.units_o_m.unit_o_m.MODE_ADD
import com.example.orderlite.presentation.units_o_m.unit_o_m.MODE_EDIT
import com.example.orderlite.presentation.units_o_m.unit_o_m.SCREEN_MODE

const val FRAGMENT_NAME_PRODUCTS_LIST = "Products List"
const val MODE_LIST_VIEW = "list_view"
const val MODE_MULTI_CHOOSE = "multi_choose"
const val ORDER_ID = "order_id"

class ListProductsFragment : Fragment() {

    private lateinit var viewModel: ListProductsViewModel
    private lateinit var rvAdapter: ListProductsRVListAdapter
    private lateinit var screenMode: String
    private var orderId: Int? = null
    private var _binding: FragmentListProductsBinding? = null
    private val binding: FragmentListProductsBinding
        get() = _binding ?: throw RuntimeException("FragmentListProductsBinding == null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentListProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentNameInstaller.setName(FRAGMENT_NAME_PRODUCTS_LIST)
        viewModel = ViewModelProvider(this)[ListProductsViewModel::class.java]
        setupRecyclerView()
        viewModel.productItemList.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
        }
        setupAddBtnOnClickListener()
    }

    private fun setupRecyclerView() {
        rvAdapter = ListProductsRVListAdapter()
        with(binding.rvListProductItems) {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
        }
        rvAdapter.productItemClickListener = {
            if (screenMode == MODE_LIST_VIEW) {
                launchProductItemFragment(ProductItemFragment.newInstance(MODE_EDIT, it.id))
            }
            else{
                TODO("Create behawior for mode choose")
            }
        }
    }

    private fun setupAddBtnOnClickListener() {
        binding.fabAddProductItem.setOnClickListener {
            launchProductItemFragment(ProductItemFragment.newInstance(MODE_ADD, 0))
        }
    }

    private fun launchProductItemFragment(fragment: ProductItemFragment) {
        parentFragmentManager.apply {
            beginTransaction()
                .replace(R.id.mainContainerView, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) throw RuntimeException("Param Screen Mode is absent")
        screenMode = args.getString(SCREEN_MODE)!!
        if (screenMode != MODE_LIST_VIEW && screenMode != MODE_MULTI_CHOOSE)
            throw RuntimeException("Unknown Screen Mode: $screenMode")
        if (screenMode == MODE_MULTI_CHOOSE) {
            if (!args.containsKey(ORDER_ID)) throw RuntimeException("OrderId is absent")
            else orderId = args.getInt(ORDER_ID)
        }
    }

    private fun launchRightMode() {
        if (screenMode == MODE_LIST_VIEW) launchListViewMode()
        else if (screenMode == MODE_MULTI_CHOOSE) launchModeMultiChoose()

    }

    private fun launchListViewMode() {
        TODO("Not yet implemented")
    }

    private fun launchModeMultiChoose() {
        TODO("Not yet implemented")
    }

    companion object {
        @JvmStatic
        fun newInstance(mode: String, orderId: Int) = ListProductsFragment().apply {
            arguments = Bundle().apply {
                putString(SCREEN_MODE, mode)
                putInt(ORDER_ID, orderId)
            }
        }
    }
}
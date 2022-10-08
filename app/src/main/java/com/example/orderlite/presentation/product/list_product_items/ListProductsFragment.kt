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

const val FRAGMENT_NAME_PRODUCTS_LIST = "Products List"

class ListProductsFragment : Fragment() {

    private lateinit var viewModel: ListProductsViewModel
    private lateinit var rvAdapter: ListProductsRVListAdapter
    private var _binding: FragmentListProductsBinding? = null
    private val binding: FragmentListProductsBinding
        get() = _binding ?: throw RuntimeException("FragmentListProductsBinding == null")


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
        viewModel.productItemList.observe(viewLifecycleOwner){
            rvAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView(){
        rvAdapter = ListProductsRVListAdapter()
        with(binding.rvListProductItems){
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
                )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListProductsFragment()
    }
}
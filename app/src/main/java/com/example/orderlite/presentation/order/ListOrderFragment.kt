package com.example.orderlite.presentation.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.orderlite.R
import com.example.orderlite.databinding.FragmentListOrderBinding
import com.example.orderlite.presentation.FragmentNameInstaller
import com.example.orderlite.presentation.MainActivity
import com.example.orderlite.presentation.order_record.order_body_list.OrderBodyFragment

class ListOrderFragment : Fragment() {

    private lateinit var binding: FragmentListOrderBinding
    private lateinit var fragmentNameInstaller: FragmentNameInstaller
    private lateinit var viewModel: ListOrderViewModel
    private lateinit var rvAdapter: ListOrderRVListAdapter

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
        fragmentNameInstaller.setName(R.string.order_list.toString())
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
            launchOrderBodyFragment(OrderBodyFragment.newInstance(it.id))
        }
    }

    private fun observeViewModel(){
        viewModel.orderList.observe(viewLifecycleOwner){
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


    companion object {
        @JvmStatic
        fun newInstance() = ListOrderFragment()
    }
}
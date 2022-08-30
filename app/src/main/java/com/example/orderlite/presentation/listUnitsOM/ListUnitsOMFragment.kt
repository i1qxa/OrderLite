package com.example.orderlite.presentation.listUnitsOM

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.orderlite.R
import com.example.orderlite.databinding.FragmentListUnitsOMBinding
import com.example.orderlite.presentation.listUnitsOM.unitOm.MODE_ADD
import com.example.orderlite.presentation.listUnitsOM.unitOm.SCREEN_MODE
import com.example.orderlite.presentation.listUnitsOM.unitOm.UnitOMFragment

class ListUnitsOMFragment : Fragment() {
    private lateinit var viewModel:ListUnitsOMViewModel
    private var _binding:FragmentListUnitsOMBinding?=null
    private val binding:FragmentListUnitsOMBinding
    get() = _binding?:throw RuntimeException("FragmentListUnitsOMBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentListUnitsOMBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ListUnitsOMViewModel::class.java]
        val recyclerView = binding.rvListUnitsOMItems
        val adapter = UnitOMRVListAdapter()
        recyclerView.adapter = adapter
        viewModel.listUnitsOM.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        setupOnClickListeners()
    }

    fun setupOnClickListeners(){
        binding.fabCreateUnitOM.setOnClickListener{
            launchUnitOMFragment(UnitOMFragment.newInstance(MODE_ADD))
        }
    }

    fun launchUnitOMFragment(fragment:UnitOMFragment){
        parentFragmentManager.apply {
            popBackStack()
            beginTransaction()
                .replace(R.id.mainContainerView,fragment)
                .addToBackStack(null)
                .commit()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListUnitsOMFragment()
    }
}
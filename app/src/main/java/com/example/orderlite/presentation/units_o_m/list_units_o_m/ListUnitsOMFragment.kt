package com.example.orderlite.presentation.units_o_m.list_units_o_m

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.orderlite.R
import com.example.orderlite.databinding.FragmentListUnitsOMBinding
import com.example.orderlite.presentation.FragmentNameInstaller
import com.example.orderlite.presentation.units_o_m.unit_o_m.MODE_ADD
import com.example.orderlite.presentation.units_o_m.unit_o_m.UnitOMFragment

const val FRAGMENT_NAME_LIST_UNITS_O_M = "List Units om"

class ListUnitsOMFragment : Fragment() {

    private lateinit var fragmentNameInstaller:FragmentNameInstaller
    private lateinit var viewModel: ListUnitsOMViewModel
    private lateinit var rvAdapter: UnitOMRVListAdapter
    private var _binding: FragmentListUnitsOMBinding? = null
    private val binding: FragmentListUnitsOMBinding
        get() = _binding ?: throw RuntimeException("FragmentListUnitsOMBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentListUnitsOMBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ListUnitsOMViewModel::class.java]
        setupRecyclerView(view.context)
        viewModel.listUnitsOM.observe(viewLifecycleOwner) {
            rvAdapter.submitList(it)
        }
        fragmentNameInstaller=FragmentNameInstaller
        fragmentNameInstaller.setName(FRAGMENT_NAME_LIST_UNITS_O_M)
    }

    private fun setupRecyclerView(context: Context) {
        rvAdapter = UnitOMRVListAdapter()
        with(binding.rvListUnitsOMItems) {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )

        }
        rvAdapter.onUnitOMClickListener = {
            launchUnitOMFragment(UnitOMFragment.newInstanceEditItem(it.id))
        }

    }

    private fun launchUnitOMFragment(fragment: UnitOMFragment) {
        parentFragmentManager.apply {
            beginTransaction()
                .replace(R.id.mainContainerView, fragment)
                .addToBackStack(null)
                .commit()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListUnitsOMFragment()
    }
}
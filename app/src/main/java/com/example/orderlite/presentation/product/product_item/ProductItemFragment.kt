package com.example.orderlite.presentation.product.product_item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.example.orderlite.databinding.FragmentProductItemBinding
import com.example.orderlite.domain.unitsOfMeasurement.UnitsOfMItem
import com.example.orderlite.presentation.FragmentNameInstaller
import com.example.orderlite.presentation.units_o_m.unit_o_m.ITEM_ID
import com.example.orderlite.presentation.units_o_m.unit_o_m.MODE_ADD
import com.example.orderlite.presentation.units_o_m.unit_o_m.MODE_EDIT
import com.example.orderlite.presentation.units_o_m.unit_o_m.SCREEN_MODE

const val FRAGMENT_NAME_PRODUCT_ITEM = "Product item"

class ProductItemFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private var screenMode: String? = null
    private var productId: Int = 0
    private var defaultUnitOM: UnitsOfMItem = UnitsOfMItem(-1, "0", "0")
    private var defaultUnitOMId: Int = -1
    private lateinit var viewModel: ProductItemViewModel
    private lateinit var fragmentNameInstaller: FragmentNameInstaller
    private lateinit var _binding: FragmentProductItemBinding
    private val binding
        get() = _binding

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        defaultUnitOM = parent?.getItemAtPosition(position) as UnitsOfMItem
        defaultUnitOMId = defaultUnitOM.id
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProductItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = androidx.lifecycle.ViewModelProvider(this)[ProductItemViewModel::class.java]
        fragmentNameInstaller = FragmentNameInstaller
        fragmentNameInstaller.setName(FRAGMENT_NAME_PRODUCT_ITEM)
        launchRightMode()
        setDefaultUnitOMClickListener()
        finishWork()
    }

    private fun finishWork() {
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }
    }

    private fun launchRightMode() {
        if (screenMode == MODE_ADD) launchModeAdd()
        else launchModeEdit()
    }

    private fun launchModeAdd() {
        binding.btnSaveProduct.setOnClickListener {
            viewModel.addProductItem(binding.etProductName.text.toString(), defaultUnitOMId)
        }

    }

    private fun setDefaultUnitOMClickListener() {
        viewModel.listUnitsOM.observe(viewLifecycleOwner) {
            val adapter = MySpinnerAdapter(this.requireContext(), it)
            val spinner = binding.spinnerUnitOM
            spinner.adapter = adapter
            spinner.onItemSelectedListener = this
        }
    }

    private fun launchModeEdit() {
        binding.btnDeleteProduct.visibility = View.VISIBLE
        binding.btnDeleteProduct.setOnClickListener {
            viewModel.deleteProductItem(productId)
        }
        binding.btnSaveProduct.setOnClickListener {
            viewModel.editProductItem(productId,
                defaultUnitOMId,
                binding.etProductName.text.toString())
        }
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE))
            throw RuntimeException("ScreenMode is Absent")
        screenMode = args.getString(SCREEN_MODE)
        if (screenMode != MODE_EDIT && screenMode != MODE_ADD)
            throw RuntimeException("Unknown ScreenMode: $screenMode")
        if (screenMode == MODE_ADD) {
            if (!args.containsKey(ITEM_ID))
                throw RuntimeException("ProductItemId is Absent")
            productId = args.getInt(ITEM_ID)
        }
    }

    companion object {
        fun newInstance(mode: String, itemId: Int) =
            ProductItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, mode)
                    putInt(ITEM_ID, itemId)
                }
            }
    }
}
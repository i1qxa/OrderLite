package com.example.orderlite.presentation.product.product_item

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.orderlite.R
import com.example.orderlite.databinding.FragmentProductItemBinding
import com.example.orderlite.domain.unitsOfMeasurement.UnitsOfMItem
import com.example.orderlite.presentation.FragmentNameInstaller
import com.example.orderlite.presentation.units_o_m.unit_o_m.ITEM_ID
import com.example.orderlite.presentation.units_o_m.unit_o_m.MODE_ADD
import com.example.orderlite.presentation.units_o_m.unit_o_m.MODE_EDIT
import com.example.orderlite.presentation.units_o_m.unit_o_m.SCREEN_MODE

class ProductItemFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private var screenMode: String? = null
    private var productId: Int = 0
    private var defaultUnitOM: UnitsOfMItem = UnitsOfMItem(-1, "0", "0")
    private var defaultUnitOMId: Int = -1
    private lateinit var spinnerAdapter: MySpinnerAdapter
    private lateinit var spinner: Spinner
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
        fragmentNameInstaller.setName(R.string.product_item.toString())
        setupSpinner()
        launchRightMode()
        setTextChangeListener()
        finishWork()
        observeErrorInput()
    }

    private fun observeErrorInput() {
        viewModel.errorInputName.observe(viewLifecycleOwner){
            if (it) binding.tilProductName.error = R.string.error_input_name.toString()
            else binding.tilProductName.error = null
        }
    }

    private fun finishWork() {
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setTextChangeListener(){
        binding.etProductName.addTextChangedListener(
            object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.resetErrorInput()
                }

                override fun afterTextChanged(s: Editable?) {

                }
            }
            )

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

    private fun setupSpinner() {
        viewModel.listUnitsOM.observe(viewLifecycleOwner) {
            spinnerAdapter = MySpinnerAdapter(this.requireContext(), it)
            spinner = binding.spinnerUnitOM
            spinner.adapter = spinnerAdapter
            spinner.onItemSelectedListener = this
            viewModel.productItem.observe(viewLifecycleOwner){ productWithUnitOM ->
                //val defaultSpinnerPosition = findUnitOMPositionInSpinner(spinner.count,productWithUnitOM.unitOMItem)
                val defaultSpinnerPosition = spinnerAdapter.getPosition(productWithUnitOM.unitOMItem)
                spinner.setSelection(defaultSpinnerPosition)
            }

        }
    }

    private fun launchModeEdit() {
        viewModel.getProductItem(productId)
        viewModel.productItem.observe(viewLifecycleOwner){
            binding.etProductName.setText(it.productItem.name)

        }
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

//    private fun findUnitOMPositionInSpinner(spinnerSize:Int, defaultUnitOM:UnitsOfMItem):Int{
//        var ans = 0
//        for (i in 0 until spinnerSize){
//            if (spinner.adapter.getItem(i)==defaultUnitOM) ans = i
//        }
//        return ans
//    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE))
            throw RuntimeException("ScreenMode is Absent")
        screenMode = args.getString(SCREEN_MODE)
        if (screenMode != MODE_EDIT && screenMode != MODE_ADD)
            throw RuntimeException("Unknown ScreenMode: $screenMode")
        if (screenMode == MODE_EDIT) {
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
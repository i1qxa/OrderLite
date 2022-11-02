package com.example.orderlite.presentation.order_record.order_record_item

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.orderlite.databinding.FragmentAddOrderRecordBinding
import com.example.orderlite.domain.unitsOfMeasurement.UnitsOfMItem
import com.example.orderlite.presentation.product.list_product_items.ORDER_ID
import com.example.orderlite.presentation.product.product_item.MySpinnerAdapter
import com.google.android.material.textfield.TextInputLayout

private const val PRODUCT_ITEM_ID = "product_item_id"
private const val UNIT_O_M_ITEM_ID = "unit_o_m_item_id"

class AddOrderRecordFragment : Fragment(), OnItemSelectedListener {
    private lateinit var viewModel: OrderRecordItemViewModel
    private var productItemId: Int? = null
    private var unitOMItemId: Int? = null
    private var orderId: Int? = null
    private lateinit var tilAmount:TextInputLayout
    private lateinit var etAmount:EditText
    private lateinit var binding: FragmentAddOrderRecordBinding
    private lateinit var spinner: Spinner
    private lateinit var spinnerAdapter: MySpinnerAdapter

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val unitOMItem = parent?.getItemAtPosition(position) as UnitsOfMItem
        unitOMItemId = unitOMItem.id
        getUnitOM()
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
        binding = FragmentAddOrderRecordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[OrderRecordItemViewModel::class.java]
        getProductItem()
        getUnitOM()
        setupSpinner()
        observeViewModel()
        setupBtnClickListeners()
        initTextInputLayout()
        setTextChangeListener()
    }

    private fun setupBtnSaveClickListener() {
        with(viewModel) {
            setOrderId(orderId)
            parseAmount(etAmount.text.toString())
            addOrderRecord()
        }
    }

    private fun setupBtnCancelClickListener() {
        viewModel.finishWork()
    }

    private fun setupBtnClickListeners() {
        setupBtnSaveClickListener()
        setupBtnCancelClickListener()
    }

    private fun getProductItem() {
        viewModel.getProductItem(
            productItemId ?: throw RuntimeException("productItemId = null")
        )
    }

    private fun initTextInputLayout(){
        etAmount = binding.etAmount
        tilAmount = binding.tilAmount
    }

    private fun setTextChangeListener(){
        etAmount.addTextChangedListener(
            object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {
                    TODO("Not yet implemented")
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.resetErrorInput()
                }

                override fun afterTextChanged(s: Editable?) {
                    TODO("Not yet implemented")
                }
            }
        )
    }

    private fun getUnitOM() {
        if (unitOMItemId != null) {
            viewModel.getUnitOMItem(
                unitOMItemId ?: throw RuntimeException("unitOMId = null")
            )
        }
    }

    private fun observeViewModel() {
        viewModel.productItem.observe(viewLifecycleOwner) {
            binding.productItemName.text = it.name
        }
        viewModel.unitOMItem.observe(viewLifecycleOwner) {
            val posDefaultUnitOM = spinnerAdapter.getPosition(it)
            spinner.setSelection(posDefaultUnitOM)
        }
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }
        viewModel.errorInputAmount.observe(viewLifecycleOwner){
            if (it) tilAmount.error = "Field cant be empty"
        }
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(PRODUCT_ITEM_ID)) throw RuntimeException("Param Product Item ID is absent")
        productItemId = args.getInt(PRODUCT_ITEM_ID)
        if (args.containsKey(UNIT_O_M_ITEM_ID)) unitOMItemId = args.getInt(UNIT_O_M_ITEM_ID)
        if (!args.containsKey(ORDER_ID)) throw RuntimeException("OrderId is absent")
        orderId = args.getInt(ORDER_ID)
    }

    private fun setupSpinner() {
        spinner = binding.unitOMSpinner
        viewModel.listUnitOM.observe(viewLifecycleOwner) {
            spinnerAdapter = MySpinnerAdapter(this.requireContext(), it)
            spinner.adapter = spinnerAdapter
            spinner.onItemSelectedListener = this
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(productItemId: Int, unitOMItemId: Int, orderId: Int) =
            AddOrderRecordFragment().apply {
                arguments = Bundle().apply {
                    putInt(PRODUCT_ITEM_ID, productItemId)
                    putInt(UNIT_O_M_ITEM_ID, unitOMItemId)
                    putInt(ORDER_ID, orderId)
                }
            }
    }
}
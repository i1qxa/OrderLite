package com.example.orderlite.presentation.order_record.order_record_item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.orderlite.databinding.DialogFragmentAmountBinding

class DialogChoseAmount : DialogFragment() {

    private var productName: String? = null
    private var unitOMName: String? = null
    private lateinit var tvProductName: TextView
    private lateinit var tvUnitOMName: TextView
    private lateinit var btnAdd: Button
    private lateinit var btnCancel: Button
    private lateinit var binding: DialogFragmentAmountBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DialogFragmentAmountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseParams()
        initViews()
        setupBtnClickListeners()
    }

    private fun setupBtnClickListeners() {
        setupBtnAddClickListener()
        setupBtnCancelClickListener()
    }

    private fun setupBtnAddClickListener() {
        btnAdd.setOnClickListener {
            parentFragmentManager.setFragmentResult(
                REQUEST_KEY,
                bundleOf(KEY_AMOUNT_RESPONSE to binding.etAmount.text.toString())
            )
            parentFragmentManager.popBackStack()
            dismissNow()
        }
    }

    private fun setupBtnCancelClickListener() {
        btnCancel.setOnClickListener {
            parentFragmentManager.popBackStack()
            dismissNow()
        }
    }

    private fun initViews() {
        btnAdd = binding.btnAddAmount
        btnCancel = binding.btnCancelDialog
        tvProductName = binding.tvProductName
        tvProductName.text = productName
        tvUnitOMName = binding.tvUnitOMName
        tvUnitOMName.text = unitOMName

    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(ARG_PRODUCT_NAME)) throw RuntimeException("Param ProductName is absent")
        productName = args.getString(ARG_PRODUCT_NAME)
        if (!args.containsKey(ARG_UNIT_O_M_NAME)) throw RuntimeException("Param UnitOM Name is absent")
        unitOMName = args.getString(ARG_UNIT_O_M_NAME)
    }

    companion object {
        @JvmStatic
        private val TAG = DialogChoseAmount::class.java.simpleName

        @JvmStatic
        val KEY_AMOUNT_RESPONSE = "KEY_AMOUNT_RESPONSE"

        @JvmStatic
        private val ARG_PRODUCT_NAME = "ARG_PRODUCT_NAME"

        @JvmStatic
        private val ARG_UNIT_O_M_NAME = "ARG_UNIT_O_M_NAME"

        @JvmStatic
        val REQUEST_KEY = "$TAG:defaultRequestKey"

        fun showAmountDialog(manager: FragmentManager, productName: String, unitOMName: String) {
            val dialogFragment = DialogChoseAmount()
            dialogFragment.arguments = bundleOf(
                ARG_PRODUCT_NAME to productName,
                ARG_UNIT_O_M_NAME to unitOMName
            )
            dialogFragment.show(manager, TAG)
        }
    }
}
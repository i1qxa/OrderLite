package com.example.orderlite.presentation.product.product_item

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.orderlite.databinding.FragmentProductItemBinding
import com.example.orderlite.presentation.FragmentNameInstaller
import com.example.orderlite.presentation.units_o_m.unit_o_m.*

const val FRAGMENT_NAME_PRODUCT_ITEM = "Product item"

class ProductItemFragment : Fragment() {
    private var screenMode: String? = null
    private var productId: Int? = null
    private lateinit var viewModel:ProductItemViewModel
    private lateinit var fragmentNameInstaller:FragmentNameInstaller

    private lateinit var _binding:FragmentProductItemBinding
    private val binding
    get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductItemBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = androidx.lifecycle.ViewModelProvider(this)[ProductItemViewModel::class.java]
        fragmentNameInstaller = FragmentNameInstaller
        fragmentNameInstaller.setName(FRAGMENT_NAME_PRODUCT_ITEM)
        launchRightMode()
    }

    private fun launchRightMode(){
        if (screenMode == MODE_ADD) launchModeAdd()
        else launchModeEdit()
    }

    private fun launchModeAdd(){

    }

    private fun setSpinner(){
        val spinner = binding.spinner
        viewModel.listUnitsOM.observe(this){
            it.forEach {

            }
        }
    }

    private fun launchModeEdit(){
        binding.btnDeleteProduct.visibility = View.VISIBLE
        binding.btnDeleteProduct.setOnClickListener{
            productId?.let { it1 -> viewModel.deleteProductItem(it1) }
        }
        binding.btnSaveProduct.setOnClickListener {

        }
    }

    private fun parseParams(){
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE))
            throw RuntimeException("ScreenMode is Absent")
        screenMode = args.getString(SCREEN_MODE)
        if (screenMode!= MODE_EDIT && screenMode != MODE_ADD)
            throw RuntimeException("Unknown ScreenMode: $screenMode")
        if (screenMode == MODE_ADD){
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
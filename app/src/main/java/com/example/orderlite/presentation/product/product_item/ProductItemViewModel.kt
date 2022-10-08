package com.example.orderlite.presentation.product.product_item

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.orderlite.data.productItem.ProductItemRepositoryImpl
import com.example.orderlite.data.unitsOM.UnitsOMRepositoryImpl
import com.example.orderlite.domain.productItem.*
import com.example.orderlite.domain.unitsOfMeasurement.GetListUnitsOMUseCase
import kotlinx.coroutines.launch

class ProductItemViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ProductItemRepositoryImpl(application)
    private val repositoryUnitOM = UnitsOMRepositoryImpl(application)

    private val getProductItemUseCase = GetProductItemUseCase(repository)
    private val addProductItemUseCase = AddProductItemUseCase(repository)
    private val deleteProductItemUseCase = DeleteProductItemUseCase(repository)
    private val editProductItemUseCase = EditProductItemUseCase(repository)

    private val getListUnitsOMUseCase = GetListUnitsOMUseCase(repositoryUnitOM)

    val listUnitsOM = getListUnitsOMUseCase.getListUnitsOM()


    private var _productItem = MutableLiveData<ProductItem>()
    val productItem: LiveData<ProductItem>
        get() = _productItem

    private var _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen:LiveData<Unit>
    get() = _shouldCloseScreen

    fun getProductItem(id: Int) {
        viewModelScope.launch {
            _productItem.value = getProductItemUseCase.getProductItem(id)
        }
    }

    fun addProductItem(name: String, unitOMId: Int) {
        if (parseName(name)) {
            val newProductItem = ProductItem(0, unitOMId, name)
            viewModelScope.launch {
                addProductItemUseCase.addProductItem(newProductItem)
                finishWork()
            }
        }
    }

    fun deleteProductItem(id: Int) {
        viewModelScope.launch {
            deleteProductItemUseCase.deleteProductItem(id)
            finishWork()
        }
    }

    fun editProductItem(productId: Int, unitOMId: Int, productName: String) {
        if (parseName(productName)) {
            val newProductItem = ProductItem(productId, unitOMId, productName)
            viewModelScope.launch {
                editProductItemUseCase.editProductItem(newProductItem)
                finishWork()
            }
        }
    }

    private fun parseName(name: String): Boolean {
        return name.isNotEmpty()
    }

    private fun finishWork(){
        _shouldCloseScreen.value = Unit
    }

}
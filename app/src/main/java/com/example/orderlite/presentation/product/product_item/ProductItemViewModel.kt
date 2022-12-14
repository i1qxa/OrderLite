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

    private val addProductItemUseCase = AddProductItemUseCase(repository)
    private val deleteProductItemUseCase = DeleteProductItemUseCase(repository)
    private val editProductItemUseCase = EditProductItemUseCase(repository)
    private val getProductItemWithUnitOMItemUseCase = GetProductItemWithUnitOMItemUseCase(repository)

    private val getListUnitsOMUseCase = GetListUnitsOMUseCase(repositoryUnitOM)

    val listUnitsOM = getListUnitsOMUseCase.getListUnitsOM()


private var _productItem = MutableLiveData<ProductItemWithUnitOMItem>()
    val productItem: LiveData<ProductItemWithUnitOMItem>
        get() = _productItem

    private var _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen:LiveData<Unit>
    get() = _shouldCloseScreen

    private var _errorInputName = MutableLiveData<Boolean>()
    val errorInputName:LiveData<Boolean>
    get() = _errorInputName

    fun getProductItem(id: Int) {
        viewModelScope.launch {
            _productItem.value = getProductItemWithUnitOMItemUseCase.getProductItemWithUnitOM(id)
        }
    }

    fun addProductItem(name: String, unitOMId: Int) {
        val productName = parseName(name)
        if (validateInput(productName)) {
            val newProductItem = ProductItem(0, unitOMId, productName)
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
        if (validateInput(productName)) {
            val prodName = parseName(productName)
            val newProductItem = ProductItem(productId, unitOMId, prodName)
            viewModelScope.launch {
                editProductItemUseCase.editProductItem(newProductItem)
                finishWork()
            }
        }
    }

    fun resetErrorInput(){
        _errorInputName.value = false
    }

    private fun parseName(name: String): String {
        return name.trim()
    }

    private fun validateInput(name: String):Boolean{
        val productName = parseName(name)
        return if (productName.isNotEmpty()){
            true
        } else{
            _errorInputName.value = true
            false
        }
    }


    private fun finishWork(){
        _shouldCloseScreen.value = Unit
    }

}
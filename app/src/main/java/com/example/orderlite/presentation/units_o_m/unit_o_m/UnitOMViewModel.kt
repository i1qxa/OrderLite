package com.example.orderlite.presentation.units_o_m.unit_o_m

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.orderlite.data.unitsOM.UnitsOMRepositoryImpl
import com.example.orderlite.domain.unitsOfMeasurement.*
import kotlinx.coroutines.launch

class UnitOMViewModel(application: Application):AndroidViewModel(application) {
    private val repository = UnitsOMRepositoryImpl(application)

    private val _unitOMItem = MutableLiveData<UnitsOfMItem>()
    val unitOMItem:LiveData<UnitsOfMItem>
    get() = _unitOMItem
    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen:LiveData<Unit>
    get() = _shouldCloseScreen
    private val _errorInputName=MutableLiveData<Boolean>()
    val errorInputName:LiveData<Boolean>
    get() = _errorInputName
    private val _errorInputShortName=MutableLiveData<Boolean>()
    val errorInputShortName:LiveData<Boolean>
        get() = _errorInputShortName

    private val addUnitOMItemUseCase = AddUnitsOMUseCase(repository)
    private val deleteUnitOMUseCase = DeleteUnitsOMUseCase(repository)
    private val editUnitOMUseCase = EditUnitsOMUseCase(repository)
    private val getUnitOMUseCase = GetUnitsOMUseCase(repository)

    fun addNewUnitOM(name:String?, shortName:String?){
        val parseName = parseNames(name)
        val parseShortName = parseNames(shortName)
        if (validateInput(parseName,parseShortName)){
            val newUnitOMItem = UnitsOfMItem(DEFAULT_ID,parseName,parseShortName)
            viewModelScope.launch {
                addUnitOMItemUseCase.addUnitsOM(newUnitOMItem)
            }
            finishWork()
        }
    }
    fun getUnitOM(unitOMId:Int){
        viewModelScope.launch {
            _unitOMItem.value =  getUnitOMUseCase.getUnitsOM(unitOMId)
        }
    }
    fun editUnitOM(id:Int,name:String,shortName:String){
        val parseName = parseNames(name)
        val parseShortName = parseNames(shortName)
        if (validateInput(parseName,parseShortName)){
            val unitOM = UnitsOfMItem(id,name,shortName)
            viewModelScope.launch {
                editUnitOMUseCase.editUnitsOM(unitOM)
            }
            finishWork()
        }

    }
    fun deleteUnitOMItem(id:Int){
        viewModelScope.launch {
            deleteUnitOMUseCase.deleteUnitsOM(id)
        }
        finishWork()
    }
    private fun finishWork(){
        _shouldCloseScreen.value = Unit
    }
    fun resetErrorInputName(){
        _errorInputName.value = false
    }
    fun resetErrorInputShortName(){
        _errorInputShortName.value = false
    }
    private fun parseNames(name:String?):String{
        return name?.trim() ?:""
    }
    private fun validateInput(name: String, shortName: String):Boolean{
        var result = true
        if (name.isBlank()){
            _errorInputName.value = true
            result = false
        }
        if (shortName.isBlank()){
            _errorInputShortName.value = true
            result = false
        }
        return result
    }

}
package com.example.orderlite.presentation.listUnitsOM.unitOm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.orderlite.data.unitsOM.UnitsOMRepositoryImpl
import com.example.orderlite.domain.unitsOfMeasurement.*
import kotlinx.coroutines.launch

class UnitOMViewModel(application: Application):AndroidViewModel(application) {

    val repository = UnitsOMRepositoryImpl(application)
    val addUnitOMItemUseCase = AddUnitsOMUseCase(repository)
    val deleteUnitOMUseCase = DeleteUnitsOMUseCase(repository)
    val editUnitOMUseCase = EditUnitsOMUseCase(repository)
    val getUnitOMUseCase = GetUnitsOMUseCase(repository)

    fun addNewUnitOM(name:String, shortName:String){
        val newUnitOMItem = UnitsOfMItem(0,name,shortName)
        viewModelScope.launch {
            addUnitOMItemUseCase.addUnitsOM(newUnitOMItem)
        }

    }
}
package com.example.orderlite.presentation.units_o_m.list_units_o_m

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.orderlite.data.unitsOM.UnitsOMRepositoryImpl
import com.example.orderlite.domain.unitsOfMeasurement.GetListUnitsOMUseCase

class ListUnitsOMViewModel(application: Application):AndroidViewModel(application) {
    private val repository = UnitsOMRepositoryImpl(application)

    private val getListUnitsOMUseCase = GetListUnitsOMUseCase(repository)

    val listUnitsOM = getListUnitsOMUseCase.getListUnitsOM()


}
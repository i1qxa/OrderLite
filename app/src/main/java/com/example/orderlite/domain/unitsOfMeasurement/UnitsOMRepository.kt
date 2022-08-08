package com.example.orderlite.domain.unitsOfMeasurement

import androidx.lifecycle.LiveData

interface UnitsOMRepository {
    fun addUnitsOM(unitsOM:UnitsOfMeasurement)

    fun deleteUnitsOM(unitsOMId:Int)

    fun editUnitsOM(unitsOM:UnitsOfMeasurement)

    fun getListUnitsOM():LiveData<List<UnitsOfMeasurement>>

    fun getUnitsOM(unitOMId:Int):UnitsOfMeasurement

}
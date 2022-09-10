package com.example.orderlite.domain.unitsOfMeasurement

import androidx.lifecycle.LiveData

interface UnitsOMRepository {
    suspend fun addUnitsOM(unitsOM:UnitsOfMItem)

    suspend fun deleteUnitsOM(unitsOMId:Int)

    suspend fun editUnitsOM(unitsOM:UnitsOfMItem)

    fun getListUnitsOM():LiveData<List<UnitsOfMItem>>

    suspend fun getUnitsOM(unitOMId:Int):UnitsOfMItem

}
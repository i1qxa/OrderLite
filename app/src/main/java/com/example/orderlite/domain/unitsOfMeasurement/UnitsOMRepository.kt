package com.example.orderlite.domain.unitsOfMeasurement

interface UnitsOMRepository {
    fun addUnitsOM(unitsOM:UnitsOfMeasurement)

    fun deleteUnitsOM(unitsOMId:Int)

    fun editUnitsOM(unitsOM:UnitsOfMeasurement)

    fun getListUnitsOM():List<UnitsOfMeasurement>

    fun getUnitsOM(unitOMId:Int):UnitsOfMeasurement

}
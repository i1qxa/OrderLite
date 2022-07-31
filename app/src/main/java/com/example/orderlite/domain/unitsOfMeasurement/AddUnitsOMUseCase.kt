package com.example.orderlite.domain.unitsOfMeasurement

class AddUnitsOMUseCase(private val unitsOMRepository: UnitsOMRepository) {
    fun addUnitsOM(unitsOM:UnitsOfMeasurement){
        unitsOMRepository.addUnitsOM(unitsOM)
    }
}
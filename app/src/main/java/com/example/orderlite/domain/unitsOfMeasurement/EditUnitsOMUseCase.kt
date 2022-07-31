package com.example.orderlite.domain.unitsOfMeasurement

class EditUnitsOMUseCase(private val unitsOMRepository: UnitsOMRepository) {
    fun editUnitsOM(unitsOM:UnitsOfMeasurement){
        unitsOMRepository.editUnitsOM(unitsOM)
    }
}
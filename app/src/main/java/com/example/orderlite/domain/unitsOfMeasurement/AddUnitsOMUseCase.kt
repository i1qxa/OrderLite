package com.example.orderlite.domain.unitsOfMeasurement

class AddUnitsOMUseCase(private val unitsOMRepository: UnitsOMRepository) {
    suspend fun addUnitsOM(unitsOM:UnitsOfMItem){
        unitsOMRepository.addUnitsOM(unitsOM)
    }
}
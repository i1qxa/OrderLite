package com.example.orderlite.domain.unitsOfMeasurement

class EditUnitsOMUseCase(private val unitsOMRepository: UnitsOMRepository) {
    suspend fun editUnitsOM(unitsOM:UnitsOfMItem){
        unitsOMRepository.editUnitsOM(unitsOM)
    }
}
package com.example.orderlite.domain.unitsOfMeasurement

class GetUnitsOMUseCase(private val unitsOMRepository: UnitsOMRepository) {
    suspend fun getUnitsOM(unitOMId:Int):UnitsOfMItem{
        return unitsOMRepository.getUnitsOM(unitOMId)
    }
}
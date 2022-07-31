package com.example.orderlite.domain.unitsOfMeasurement

class GetUnitsOMUseCase(private val unitsOMRepository: UnitsOMRepository) {
    fun getUnitsOM(unitOMId:Int):UnitsOfMeasurement{
        return unitsOMRepository.getUnitsOM(unitOMId)
    }
}
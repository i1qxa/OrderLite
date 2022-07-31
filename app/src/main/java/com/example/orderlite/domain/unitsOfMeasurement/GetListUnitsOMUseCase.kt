package com.example.orderlite.domain.unitsOfMeasurement

class GetListUnitsOMUseCase(private val unitsOMRepository: UnitsOMRepository) {
    fun getListUnitsOM():List<UnitsOfMeasurement>{
        return unitsOMRepository.getListUnitsOM()
    }
}
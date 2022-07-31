package com.example.orderlite.domain.unitsOfMeasurement

class DeleteUnitsOMUseCase(private val unitsOMRepository: UnitsOMRepository) {
    fun deleteUnitsOM(unitsOMId:Int){
        unitsOMRepository.deleteUnitsOM(unitsOMId)
    }
}
package com.example.orderlite.domain.unitsOfMeasurement

import androidx.lifecycle.LiveData

class GetListUnitsOMUseCase(private val unitsOMRepository: UnitsOMRepository) {
    fun getListUnitsOM():LiveData<List<UnitsOfMeasurement>>{
        return unitsOMRepository.getListUnitsOM()
    }
}
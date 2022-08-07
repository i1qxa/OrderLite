package com.example.orderlite.data.unitsOM

import com.example.orderlite.domain.unitsOfMeasurement.UnitsOfMeasurement

class UnitsOMMapper {
    fun mapUnitOMTODB(unitOM: UnitsOfMeasurement):UnitsOMDbModel{
        return UnitsOMDbModel(
            id = unitOM.id,
            name = unitOM.name,
            shortName = unitOM.shortName
        )
    }
    fun mapDBToUnitOM(unitOMDb: UnitsOMDbModel):UnitsOfMeasurement{
        return UnitsOfMeasurement(
            id = unitOMDb.id,
            name = unitOMDb.name, 
            shortName = unitOMDb.shortName
        )
    }
    fun mapListDBToListUnitsOM(list:List<UnitsOMDbModel>) = list.map { mapDBToUnitOM(it) }
}
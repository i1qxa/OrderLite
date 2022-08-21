package com.example.orderlite.data.unitsOM

import com.example.orderlite.domain.unitsOfMeasurement.UnitsOfMeasurement

class UnitsOMMapper {
    fun mapUnitOMTODB(unitOM: UnitsOfMeasurement):UnitsOMDbModel{
        return UnitsOMDbModel(
            unitId = unitOM.id,
            unitName = unitOM.name,
            unitShortName = unitOM.shortName
        )
    }
    fun mapDBToUnitOM(unitOMDb: UnitsOMDbModel):UnitsOfMeasurement{
        return UnitsOfMeasurement(
            id = unitOMDb.unitId,
            name = unitOMDb.unitName,
            shortName = unitOMDb.unitShortName
        )
    }
    fun mapListDBToListUnitsOM(list:List<UnitsOMDbModel>) = list.map { mapDBToUnitOM(it) }
}
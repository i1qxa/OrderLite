package com.example.orderlite.data.unitsOM

import android.app.Application
import com.example.orderlite.data.AppDatabase
import com.example.orderlite.domain.unitsOfMeasurement.UnitsOMRepository
import com.example.orderlite.domain.unitsOfMeasurement.UnitsOfMeasurement

class UnitsOMRepositoryImpl(application: Application):UnitsOMRepository {

    private val unitsOMDbModelDao = AppDatabase.getInstance(application).unitsOMDBModelDao()
    private val mapper = UnitsOMMapper()

    override fun addUnitsOM(unitsOM: UnitsOfMeasurement) {
        unitsOMDbModelDao.addUnitOM(mapper.mapUnitOMTODB(unitsOM))
    }

    override fun deleteUnitsOM(unitsOMId: Int) {
        unitsOMDbModelDao.deleteUnitOM(unitsOMId)
    }

    override fun editUnitsOM(unitsOM: UnitsOfMeasurement) {
        unitsOMDbModelDao.addUnitOM(mapper.mapUnitOMTODB(unitsOM))
    }

    override fun getListUnitsOM(): List<UnitsOfMeasurement> {
        TODO("Not yet implemented")
    }

    override fun getUnitsOM(unitOMId: Int): UnitsOfMeasurement {
        val unitOMDB = unitsOMDbModelDao.getUnitOM(unitOMId)
        return mapper.mapDBToUnitOM(unitOMDB)
    }
}
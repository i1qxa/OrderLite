package com.example.orderlite.data.unitsOM

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
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

    override fun getListUnitsOM(): LiveData<List<UnitsOfMeasurement>> = Transformations
        .map(unitsOMDbModelDao.getUnitsOMList()){
            mapper.mapListDBToListUnitsOM(it)
        }

    override fun getUnitsOM(unitOMId: Int): UnitsOfMeasurement {
        val unitOMDB = unitsOMDbModelDao.getUnitOM(unitOMId)
        return mapper.mapDBToUnitOM(unitOMDB)
    }
}
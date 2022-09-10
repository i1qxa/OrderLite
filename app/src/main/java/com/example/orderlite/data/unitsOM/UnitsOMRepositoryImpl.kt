package com.example.orderlite.data.unitsOM

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.orderlite.data.AppDatabase
import com.example.orderlite.domain.unitsOfMeasurement.UnitsOMRepository
import com.example.orderlite.domain.unitsOfMeasurement.UnitsOfMItem

class UnitsOMRepositoryImpl(application: Application):UnitsOMRepository {

    private val unitsOMDbModelDao = AppDatabase.getInstance(application).unitsOMDBModelDao()
    private val mapper = UnitsOMMapper()

    override suspend fun addUnitsOM(unitsOM: UnitsOfMItem) {
        unitsOMDbModelDao.addUnitOM(mapper.mapUnitOMTODB(unitsOM))
    }

    override suspend fun deleteUnitsOM(unitsOMId: Int) {
        unitsOMDbModelDao.deleteUnitOM(unitsOMId)
    }

    override suspend fun editUnitsOM(unitsOM: UnitsOfMItem) {
        unitsOMDbModelDao.addUnitOM(mapper.mapUnitOMTODB(unitsOM))
    }

    override fun getListUnitsOM(): LiveData<List<UnitsOfMItem>> = Transformations
        .map(unitsOMDbModelDao.getUnitsOMList()){
            mapper.mapListDBToListUnitsOM(it)
        }

    override suspend fun getUnitsOM(unitOMId: Int): UnitsOfMItem {
        val unitOMDB = unitsOMDbModelDao.getUnitOM(unitOMId)
        return mapper.mapDBToUnitOM(unitOMDB)
    }
}
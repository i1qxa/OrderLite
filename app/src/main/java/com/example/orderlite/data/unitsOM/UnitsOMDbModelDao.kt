package com.example.orderlite.data.unitsOM

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UnitsOMDbModelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUnitOM(unitsOM: UnitsOMDbModel)
    @Query("DELETE FROM units_o_m WHERE unit_id = :unitOMId")
    suspend fun deleteUnitOM(unitOMId:Int)
    @Query("SELECT * FROM units_o_m")
    fun getUnitsOMList():LiveData<List<UnitsOMDbModel>>
    @Query("SELECT * FROM units_o_m where unit_id = :unitOMID")
    suspend fun getUnitOM(unitOMID:Int):UnitsOMDbModel
}
package com.example.cars.data.database.manufacturers

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ManufacturerInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addManufacturerItem(manufacturerInfoDbModel: ManufacturerInfoDbModel)

    @Query("SELECT * FROM manufacturerInfo")
    fun getManufacturerList(): LiveData<List<ManufacturerInfoDbModel>>

    @Query("SELECT * FROM manufacturerInfo WHERE id=:manufacturerItemId LIMIT 1")
    suspend fun getManufacturerItem(manufacturerItemId: Int): ManufacturerInfoDbModel

    @Query("DELETE FROM manufacturerInfo WHERE id=:manufacturerItemId")
    suspend fun deleteManufacturerItem(manufacturerItemId: Int)
}
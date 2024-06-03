package com.example.cars.data.database.carModel

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CarModelInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCarModelItem(carModelInfoDbModel: CarModelInfoDbModel)

    @Query("SELECT * FROM carModelInfo")
    fun getCarModelList(): LiveData<List<CarModelInfoDbModel>>

    @Query("SELECT * FROM carModelInfo WHERE manufacturerName=:manufacturerName")
    fun getCarModelListByManufacturer(manufacturerName: String): LiveData<List<CarModelInfoDbModel>>

    @Query("SELECT * FROM carModelInfo WHERE id=:carModelItemId LIMIT 1")
    suspend fun getCarModelItem(carModelItemId: Int): CarModelInfoDbModel

    @Query("DELETE FROM carModelInfo WHERE id=:carModelItemId")
    suspend fun deleteCarModelItem(carModelItemId: Int)
}
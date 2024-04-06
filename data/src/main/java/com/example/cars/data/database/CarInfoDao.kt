package com.example.cars.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CarInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCarItem(carInfoDbModel: CarInfoDbModel)

    @Query("SELECT * FROM carInfo")
    fun getCarList(): LiveData<List<CarInfoDbModel>>

    @Query("SELECT * FROM carInfo WHERE id=:carItemId LIMIT 1")
    suspend fun getCarItem(carItemId: Int): CarInfoDbModel

    @Query("DELETE FROM carInfo WHERE id=:carItemId")
    suspend fun deleteCarItem(carItemId: Int)
}
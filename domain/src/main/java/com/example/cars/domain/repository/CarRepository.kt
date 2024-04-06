package com.example.cars.domain.repository

import androidx.lifecycle.LiveData
import com.example.cars.domain.models.CarItem

interface CarRepository {
    fun getCarList(): LiveData<List<CarItem>>
    suspend fun addCar(carItem: CarItem)
    suspend fun editCar(carItem: CarItem)
    suspend fun deleteCar(carItem: CarItem)
    suspend fun getCarItem(carItemId: Int): CarItem
}
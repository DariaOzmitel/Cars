package com.example.cars.domain.repository

import androidx.lifecycle.LiveData
import com.example.cars.domain.models.CarItem
import com.example.cars.domain.models.CarModelItem
import com.example.cars.domain.models.ManufacturerItem

interface CarRepository {
    fun getCarList(): LiveData<List<CarItem>>
    suspend fun <T> addCar(itemClass: Class<T>, carItem: T)
    suspend fun addManufacturer(manufacturerItem: ManufacturerItem)
    suspend fun addCarModel(carModelItem: CarModelItem)
    suspend fun editCar(carItem: CarItem)
    suspend fun deleteCar(carItem: CarItem)
    suspend fun getCarItem(carItemId: Int): CarItem
}
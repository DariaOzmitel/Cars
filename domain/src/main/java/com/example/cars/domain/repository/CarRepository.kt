package com.example.cars.domain.repository

import androidx.lifecycle.LiveData
import com.example.cars.domain.models.CarItem
import com.example.cars.domain.models.CarModelItem2
import com.example.cars.domain.models.Item
import com.example.cars.domain.models.ManufacturerItem2

interface CarRepository {
    fun getCarList(): LiveData<List<CarItem>>
    suspend fun addCar(item: Item)
    suspend fun addManufacturer(manufacturerItem2: ManufacturerItem2)
    suspend fun addCarModel(carModelItem2: CarModelItem2)
    suspend fun getCarItem(itemId: Int): CarItem
    suspend fun editCar(item: Item)
    suspend fun deleteCar(item: Item)
}
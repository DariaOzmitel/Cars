package com.example.cars.domain.repository

import androidx.lifecycle.LiveData
import com.example.cars.domain.models.CarItem

interface CarRepository {
    fun getCarList(): LiveData<List<CarItem>>
    fun addCar(carItem: CarItem)
    fun editCar(carItem: CarItem)
    fun deleteCar(carItem: CarItem)
    fun getCarItem(carItemId: Int): CarItem
}
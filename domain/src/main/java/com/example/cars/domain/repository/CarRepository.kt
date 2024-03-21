package com.example.cars.domain.repository

import com.example.cars.domain.models.CarItem

interface CarRepository {
    fun getCarList(): List<CarItem>
    fun addCar(carItem: CarItem)
    fun editCar(carItem: CarItem)
    fun deleteCar(carItem: CarItem)
    fun getCarItem(carItemId: Int): CarItem
}
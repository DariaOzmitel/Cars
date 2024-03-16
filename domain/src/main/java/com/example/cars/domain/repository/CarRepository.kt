package com.example.cars.domain.repository

import com.example.cars.domain.models.Car

interface CarRepository {
    fun getCarList(): List<Car>
    fun addCar(car: Car)
    fun editCar(car: Car)
    fun deleteCar(car: Car)
    fun getCarItem(carItemId: Int): Car
}
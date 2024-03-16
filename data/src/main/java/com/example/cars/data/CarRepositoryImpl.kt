package com.example.cars.data

import com.example.cars.domain.models.Car
import com.example.cars.domain.repository.CarRepository
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(

): CarRepository {
    override fun getCarList(): List<Car> {
        TODO("Not yet implemented")
    }

    override fun addCar(car: Car) {
        TODO("Not yet implemented")
    }

    override fun editCar(car: Car) {
        TODO("Not yet implemented")
    }

    override fun deleteCar(car: Car) {
        TODO("Not yet implemented")
    }

    override fun getCarItem(carItemId: Int): Car {
        TODO("Not yet implemented")
    }
}
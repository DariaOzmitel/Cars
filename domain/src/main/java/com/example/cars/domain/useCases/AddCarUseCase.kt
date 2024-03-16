package com.example.cars.domain.useCases

import com.example.cars.domain.models.Car
import com.example.cars.domain.repository.CarRepository
import javax.inject.Inject

class AddCarUseCase @Inject constructor(
    private val repository: CarRepository
) {
    fun addCar(car: Car) {
        repository.addCar(car)
    }
}
package com.example.cars.domain.useCases

import com.example.cars.domain.models.Car
import com.example.cars.domain.repository.CarRepository
import javax.inject.Inject

class EditCarUseCase @Inject constructor(
    private val repository: CarRepository
) {
    fun editCar(car: Car) {
        repository.editCar(car)
    }
}
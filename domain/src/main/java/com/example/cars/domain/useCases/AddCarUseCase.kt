package com.example.cars.domain.useCases

import com.example.cars.domain.models.CarItem
import com.example.cars.domain.repository.CarRepository
import javax.inject.Inject

class AddCarUseCase @Inject constructor(
    private val repository: CarRepository
) {
    suspend fun addCar(carItem: CarItem) {
        repository.addCar(carItem)
    }
}
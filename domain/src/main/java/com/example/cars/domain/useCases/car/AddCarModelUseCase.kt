package com.example.cars.domain.useCases.car

import com.example.cars.domain.models.CarModelItem2
import com.example.cars.domain.repository.CarRepository
import javax.inject.Inject

class AddCarModelUseCase @Inject constructor(
    private val repository: CarRepository
) {
    suspend fun addCarModel(carModelItem2: CarModelItem2) {
        repository.addCarModel(carModelItem2)
    }
}
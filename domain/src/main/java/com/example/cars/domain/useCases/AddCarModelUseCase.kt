package com.example.cars.domain.useCases

import com.example.cars.domain.models.CarModelItem
import com.example.cars.domain.repository.CarRepository
import javax.inject.Inject

class AddCarModelUseCase @Inject constructor(
    private val repository: CarRepository
) {
    suspend fun addCarModel(carModelItem: CarModelItem) {
        repository.addCarModel(carModelItem)
    }
}
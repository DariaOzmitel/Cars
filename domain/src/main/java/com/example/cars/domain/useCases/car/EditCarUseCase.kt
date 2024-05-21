package com.example.cars.domain.useCases.car

import com.example.cars.domain.models.CarItem
import com.example.cars.domain.repository.CarRepository
import javax.inject.Inject

class EditCarUseCase @Inject constructor(
    private val repository: CarRepository
) {
    suspend fun editCar(carItem: CarItem) {
        repository.editCar(carItem)
    }
}
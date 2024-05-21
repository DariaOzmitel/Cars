package com.example.cars.domain.useCases.car

import com.example.cars.domain.models.CarItem
import com.example.cars.domain.repository.CarRepository
import javax.inject.Inject

class AddCarUseCase @Inject constructor(
    private val repository: CarRepository
) {
    suspend fun <T> addCar(itemClass: Class<T>, item: T) {
        repository.addCar(itemClass, item)
    }
}
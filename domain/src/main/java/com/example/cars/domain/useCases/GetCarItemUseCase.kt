package com.example.cars.domain.useCases

import com.example.cars.domain.models.Car
import com.example.cars.domain.repository.CarRepository
import javax.inject.Inject

class GetCarItemUseCase @Inject constructor(
    private val repository: CarRepository
) {
    fun getCarItem(carItemId: Int): Car {
        return repository.getCarItem(carItemId)
    }
}
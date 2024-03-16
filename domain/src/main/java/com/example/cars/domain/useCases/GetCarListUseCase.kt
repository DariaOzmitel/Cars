package com.example.cars.domain.useCases

import com.example.cars.domain.models.Car
import com.example.cars.domain.repository.CarRepository
import javax.inject.Inject

class GetCarListUseCase @Inject constructor(
    private val repository: CarRepository
) {
    fun getCarList(): List<Car> {
        return repository.getCarList()
    }
}
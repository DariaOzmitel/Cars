package com.example.cars.domain.useCases

import com.example.cars.domain.models.CarItem
import com.example.cars.domain.repository.CarRepository
import javax.inject.Inject

class GetCarListUseCase @Inject constructor(
    private val repository: CarRepository
) {
    fun getCarList(): List<CarItem> {
        return repository.getCarList()
    }
}
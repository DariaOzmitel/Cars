package com.example.cars.domain.useCases

import com.example.cars.domain.models.CarItem
import com.example.cars.domain.repository.CarRepository
import javax.inject.Inject

class GetCarItemUseCase @Inject constructor(
    private val repository: CarRepository
) {
    fun getCarItem(carItemId: Int): CarItem {
        return repository.getCarItem(carItemId)
    }
}
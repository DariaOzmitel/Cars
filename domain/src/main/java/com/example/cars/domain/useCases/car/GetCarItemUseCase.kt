package com.example.cars.domain.useCases.car

import com.example.cars.domain.models.CarItem
import com.example.cars.domain.repository.CarRepository
import javax.inject.Inject

class GetCarItemUseCase @Inject constructor(
    private val repository: CarRepository
) {
    suspend fun getCarItem(itemId: Int): CarItem {
        return repository.getCarItem(itemId)
    }
}
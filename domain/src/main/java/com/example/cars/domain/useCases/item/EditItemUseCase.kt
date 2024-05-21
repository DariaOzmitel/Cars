package com.example.cars.domain.useCases.item

import com.example.cars.domain.models.Item
import com.example.cars.domain.repository.CarRepository
import javax.inject.Inject

class EditItemUseCase @Inject constructor(
    private val repository: CarRepository
) {
    suspend fun editCar(item: Item) {
        repository.editCar(item)
    }
}
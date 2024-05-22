package com.example.cars.domain.useCases.item

import com.example.cars.domain.models.Item
import com.example.cars.domain.repository.CarRepository
import javax.inject.Inject

class AddItemUseCase @Inject constructor(
    private val repository: CarRepository
) {
    suspend fun addItem(item: Item) {
        repository.addItem(item)
    }
}
package com.example.cars.domain.useCases.item

import com.example.cars.domain.models.Item
import com.example.cars.domain.repository.CarRepository
import javax.inject.Inject
import kotlin.reflect.KClass

class GetItemUseCase @Inject constructor(
    private val repository: CarRepository
) {
    suspend fun <T : Item> getItem(itemClass: KClass<T>, itemId: Int): Item {
        return repository.getItem(itemClass, itemId)
    }
}
package com.example.cars.domain.useCases.item

import androidx.lifecycle.LiveData
import com.example.cars.domain.models.Item
import com.example.cars.domain.repository.CarRepository
import javax.inject.Inject
import kotlin.reflect.KClass

class GetItemListUseCase @Inject constructor(
    private val repository: CarRepository
) {
    fun <T : Item> getItemList(itemClass: KClass<T>): LiveData<List<Item>> {
        return repository.getItemList(itemClass)
    }
}
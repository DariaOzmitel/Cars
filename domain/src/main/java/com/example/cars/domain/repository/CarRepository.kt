package com.example.cars.domain.repository

import androidx.lifecycle.LiveData
import com.example.cars.domain.models.Item
import kotlin.reflect.KClass

interface CarRepository {
    fun <T : Item> getItemList(itemClass: KClass<T>): LiveData<List<Item>>
    suspend fun addItem(item: Item)
    suspend fun editItem(item: Item)
    suspend fun deleteItem(item: Item)
    suspend fun <T : Item> getItem(itemClass: KClass<T>, itemId: Int): Item
}
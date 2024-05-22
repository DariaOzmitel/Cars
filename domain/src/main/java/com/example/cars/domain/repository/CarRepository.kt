package com.example.cars.domain.repository

import androidx.lifecycle.LiveData
import com.example.cars.domain.models.CarModelItem2
import com.example.cars.domain.models.Item
import com.example.cars.domain.models.ManufacturerItem2
import kotlin.reflect.KClass

interface CarRepository {
    fun <T : Item> getItemList(itemClass: KClass<T>): LiveData<List<Item>>
    suspend fun addItem(item: Item)
    suspend fun editItem(item: Item)
    suspend fun deleteItem(item: Item)
    suspend fun addManufacturer(manufacturerItem2: ManufacturerItem2)
    suspend fun addCarModel(carModelItem2: CarModelItem2)
    suspend fun <T : Item> getItem(itemClass: KClass<T>, itemId: Int): Item
}
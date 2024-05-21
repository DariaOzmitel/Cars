package com.example.cars.domain.repository

import androidx.lifecycle.LiveData
import com.example.cars.domain.models.CarItem
import com.example.cars.domain.models.CarModelItem
import com.example.cars.domain.models.ManufacturerItem

interface ManufacturerRepository {
    fun getManufacturerList(): LiveData<List<ManufacturerItem>>
    suspend fun addManufacturer(manufacturerItem: ManufacturerItem)
    suspend fun editManufacturer(manufacturerItem: ManufacturerItem)
    suspend fun deleteManufacturer(manufacturerItem: ManufacturerItem)
    suspend fun getManufacturerItem(manufacturerItemId: Int): ManufacturerItem
}
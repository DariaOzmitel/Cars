package com.example.cars.domain.repository

import androidx.lifecycle.LiveData
import com.example.cars.domain.models.ManufacturerItem2

interface ManufacturerRepository {
    fun getManufacturerList(): LiveData<List<ManufacturerItem2>>
    suspend fun addManufacturer(manufacturerItem2: ManufacturerItem2)
    suspend fun editManufacturer(manufacturerItem2: ManufacturerItem2)
    suspend fun deleteManufacturer(manufacturerItem2: ManufacturerItem2)
    suspend fun getManufacturerItem(manufacturerItemId: Int): ManufacturerItem2
}
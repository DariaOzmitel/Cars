package com.example.cars.domain.useCases.manufacturer

import com.example.cars.domain.models.ManufacturerItem2
import com.example.cars.domain.repository.CarRepository
import javax.inject.Inject

class AddManufacturerUseCas @Inject constructor(
    private val repository: CarRepository
) {
    suspend fun addManufacturer(manufacturerItem2: ManufacturerItem2) {
        repository.addManufacturer(manufacturerItem2)
    }
}
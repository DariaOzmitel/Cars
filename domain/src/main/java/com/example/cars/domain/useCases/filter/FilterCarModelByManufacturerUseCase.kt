package com.example.cars.domain.useCases.filter

import androidx.lifecycle.LiveData
import com.example.cars.domain.models.CarModelItem
import com.example.cars.domain.repository.CarRepository
import javax.inject.Inject

class FilterCarModelByManufacturerUseCase @Inject constructor(
    private val repository: CarRepository
) {
    fun filterCarModelByManufacturer(manufacturerName: String): LiveData<List<CarModelItem>> {
        return repository.filterCarModelByManufacturer(manufacturerName)
    }
}
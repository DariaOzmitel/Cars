package com.example.cars

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cars.domain.models.CarItem
import com.example.cars.domain.useCases.AddCarUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CarItemViewModel @Inject constructor(
    private val addCarUseCase: AddCarUseCase
) : ViewModel() {

    fun addCarItem(inputManufacturer: String, inputCarModel: String) {
        viewModelScope.launch {
            val carItem = CarItem(manufacturer = inputManufacturer, carModel = inputCarModel)
            addCarUseCase.addCar(carItem)
        }
    }

}
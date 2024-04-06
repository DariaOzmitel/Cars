package com.example.cars

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cars.domain.models.CarItem
import com.example.cars.domain.useCases.DeleteCarUseCase
import com.example.cars.domain.useCases.GetCarListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCarListUseCase: GetCarListUseCase,
    private val deleteCarUseCase: DeleteCarUseCase
) : ViewModel() {
    val carList = getCarListUseCase.getCarList()

    fun deleteCarItem(carItem: CarItem) {
        viewModelScope.launch {
            deleteCarUseCase.deleteCar(carItem)
        }
    }
}
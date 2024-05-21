package com.example.cars.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cars.domain.models.CarItem
import com.example.cars.domain.useCases.car.GetCarListUseCase
import com.example.cars.domain.useCases.item.DeleteItemUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ManufacturerListViewModel @Inject constructor(
    private val getCarListUseCase: GetCarListUseCase,
    private val deleteItemUseCase: DeleteItemUseCase
) : ViewModel() {
    val carList = getCarListUseCase.getCarList()

    fun deleteCarItem(carItem: CarItem) {
        viewModelScope.launch {
            deleteItemUseCase.deleteCar(carItem)
        }
    }
}
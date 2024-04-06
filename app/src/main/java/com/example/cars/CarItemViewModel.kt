package com.example.cars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cars.domain.models.CarItem
import com.example.cars.domain.useCases.AddCarUseCase
import com.example.cars.domain.useCases.EditCarUseCase
import com.example.cars.domain.useCases.GetCarItemUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CarItemViewModel @Inject constructor(
    private val addCarUseCase: AddCarUseCase,
    private val getCarItemUseCase: GetCarItemUseCase,
    private val editCarUseCase: EditCarUseCase
) : ViewModel() {

    private val _carItem = MutableLiveData<CarItem>()
    val carItem: LiveData<CarItem>
        get() = _carItem

    fun addCarItem(inputManufacturer: String, inputCarModel: String) {
        viewModelScope.launch {
            val carItem = CarItem(manufacturer = inputManufacturer, carModel = inputCarModel)
            addCarUseCase.addCar(carItem)
        }
    }

    fun getCarItem(carItemId: Int) {
        viewModelScope.launch {
            _carItem.value = getCarItemUseCase.getCarItem(carItemId)
        }
    }

    fun editCarItem(inputManufacturer: String, inputCarModel: String) {
        _carItem.value?.let {
            viewModelScope.launch {
                val carItem = it.copy(manufacturer = inputManufacturer, carModel = inputCarModel)
                editCarUseCase.editCar(carItem)
            }
        }

    }

}
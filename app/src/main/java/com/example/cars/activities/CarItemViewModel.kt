package com.example.cars.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cars.CarItemInfo
import com.example.cars.CarItemState
import com.example.cars.CloseScreen
import com.example.cars.ErrorInputCarModel
import com.example.cars.ErrorInputManufacturer
import com.example.cars.domain.models.CarItem
import com.example.cars.domain.useCases.car.GetCarItemUseCase
import com.example.cars.domain.useCases.item.AddItemUseCase
import com.example.cars.domain.useCases.item.EditItemUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CarItemViewModel @Inject constructor(
    private val addItemUseCase: AddItemUseCase,
    private val getCarItemUseCase: GetCarItemUseCase,
    private val editItemUseCase: EditItemUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<CarItemState>(
        CarItemInfo(CarItem(manufacturer = "", carModel = ""))
    )
    val state = _state.asStateFlow()

    private lateinit var carItem: CarItem

    fun addCarItem(inputManufacturer: String, inputCarModel: String) {
        val manufacturer = parseString(inputManufacturer)
        val carModel = parseString(inputCarModel)
        val fieldsValid = validateInput(manufacturer, carModel)
        if (fieldsValid) {
            viewModelScope.launch {
                val carItem = CarItem(manufacturer = manufacturer, carModel = carModel)
                addItemUseCase.addCar(carItem)
            }
            finishWork()
        }
    }

    fun getCarItem(carItemId: Int) {
        viewModelScope.launch {
            carItem = getCarItemUseCase.getCarItem(carItemId)
            _state.value = CarItemInfo(carItem)
        }
    }

    fun editCarItem(inputManufacturer: String, inputCarModel: String) {
        val manufacturer = parseString(inputManufacturer)
        val carModel = parseString(inputCarModel)
        val fieldsValid = validateInput(manufacturer, carModel)
        if (fieldsValid) {
            viewModelScope.launch {
                carItem = carItem.copy(manufacturer = manufacturer, carModel = carModel)
                editItemUseCase.editCar(carItem)
            }
            finishWork()
        }
    }

    private fun parseString(inputString: String?): String {
        return inputString?.trim() ?: ""
    }

    private fun validateInput(inputManufacturer: String, inputCarModel: String): Boolean {
        var result = true
        if (inputManufacturer.isBlank()) {
            _state.value = ErrorInputManufacturer(true)
            result = false
        }
        if (inputCarModel.isBlank()) {
            _state.value = ErrorInputCarModel(true)
            result = false
        }
        return result
    }

    fun resetErrorInputManufacturer() {
        _state.value = ErrorInputManufacturer(false)
    }

    fun resetErrorInputCarModel() {
        _state.value = ErrorInputCarModel(false)
    }

    fun finishWork() {
        _state.value = CloseScreen
    }

}
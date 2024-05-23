package com.example.cars.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cars.domain.models.CarItem
import com.example.cars.domain.useCases.item.AddItemUseCase
import com.example.cars.domain.useCases.item.EditItemUseCase
import com.example.cars.domain.useCases.item.GetItemUseCase
import com.example.cars.state.CarItemInfo
import com.example.cars.state.CarItemState
import com.example.cars.state.CloseScreen
import com.example.cars.state.ErrorInputCarModel
import com.example.cars.state.ErrorInputManufacturer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CarItemViewModel @Inject constructor(
    private val addItemUseCase: AddItemUseCase,
    private val getItemUseCase: GetItemUseCase,
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
                addItemUseCase.addItem(carItem)
            }
            finishWork()
        }
    }

    fun getCarItem(carItemId: Int) {
        viewModelScope.launch {
            carItem = getItemUseCase.getItem(CarItem::class, carItemId) as CarItem
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
                editItemUseCase.editItem(carItem)
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

    private fun finishWork() {
        _state.value = CloseScreen
    }

}
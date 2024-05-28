package com.example.cars.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cars.domain.models.CarModelItem
import com.example.cars.domain.models.ManufacturerItem
import com.example.cars.domain.useCases.item.AddItemUseCase
import com.example.cars.domain.useCases.item.EditItemUseCase
import com.example.cars.domain.useCases.item.GetItemListUseCase
import com.example.cars.domain.useCases.item.GetItemUseCase
import com.example.cars.state.CarModelItemInfo
import com.example.cars.state.CarModelItemState
import com.example.cars.state.CloseCarModelItemScreen
import com.example.cars.state.ErrorInputCarModelName
import com.example.cars.state.ErrorInputManufacturerItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CarModelItemViewModel @Inject constructor(
    private val addItemUseCase: AddItemUseCase,
    private val getItemUseCase: GetItemUseCase,
    private val editItemUseCase: EditItemUseCase,
    private val getItemListUseCase: GetItemListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<CarModelItemState>(
        CarModelItemInfo(CarModelItem(manufacturerName = "", carModelName = ""))
    )
    val state = _state.asStateFlow()

    private lateinit var carModelItem: CarModelItem

    val manufacturerList =
        getItemListUseCase.getItemList(ManufacturerItem::class) as LiveData<List<ManufacturerItem>>

    fun addCarModelItem(inputManufacturer: String, inputCarModel: String) {
        val manufacturer = parseString(inputManufacturer)
        val carModel = parseString(inputCarModel)
        val fieldsValid = validateInput(manufacturer, carModel)
        if (fieldsValid) {
            viewModelScope.launch {
                val carModelItem =
                    CarModelItem(manufacturerName = manufacturer, carModelName = carModel)
                addItemUseCase.addItem(carModelItem)
            }
            finishWork()
        }
    }

    fun getCarModelItem(carModelItemId: Int) {
        viewModelScope.launch {
            carModelItem =
                getItemUseCase.getItem(CarModelItem::class, carModelItemId) as CarModelItem
            _state.value = CarModelItemInfo(carModelItem)
        }
    }

    fun editCarModelItem(inputManufacturer: String, inputCarModel: String) {
        val manufacturer = parseString(inputManufacturer)
        val carModel = parseString(inputCarModel)
        val fieldsValid = validateInput(manufacturer, carModel)
        if (fieldsValid) {
            viewModelScope.launch {
                carModelItem =
                    carModelItem.copy(manufacturerName = inputManufacturer, carModelName = carModel)
                editItemUseCase.editItem(carModelItem)
            }
            finishWork()
        }
    }

    private fun parseString(inputString: String?): String {
        return inputString?.trim() ?: ""
    }

    private fun validateInput(inputManufacturer: String, inputCarModelModel: String): Boolean {
        var result = true
        if (inputManufacturer.isBlank()) {
            _state.value = ErrorInputManufacturerItem(true)
            result = false
        }
        if (inputCarModelModel.isBlank()) {
            _state.value = ErrorInputCarModelName(true)
            result = false
        }
        return result
    }

    fun resetErrorInputManufacturerItem() {
        _state.value = ErrorInputManufacturerItem(false)
    }

    fun resetErrorInputCarModelName() {
        _state.value = ErrorInputCarModelName(false)
    }

    private fun finishWork() {
        _state.value = CloseCarModelItemScreen
    }

}
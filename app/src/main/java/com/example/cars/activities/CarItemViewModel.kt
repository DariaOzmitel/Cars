package com.example.cars.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cars.domain.models.CarItem
import com.example.cars.domain.models.CarModelItem
import com.example.cars.domain.models.ManufacturerItem
import com.example.cars.domain.useCases.item.AddItemUseCase
import com.example.cars.domain.useCases.item.EditItemUseCase
import com.example.cars.domain.useCases.item.GetItemListUseCase
import com.example.cars.domain.useCases.item.GetItemUseCase
import com.example.cars.state.CarItemInfo
import com.example.cars.state.CarItemState
import com.example.cars.state.CloseScreen
import com.example.cars.state.ErrorInputCarModel
import com.example.cars.state.ErrorInputManufacturer
import com.example.cars.state.ErrorInputPrice
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CarItemViewModel @Inject constructor(
    private val addItemUseCase: AddItemUseCase,
    private val getItemUseCase: GetItemUseCase,
    private val editItemUseCase: EditItemUseCase,
    val getItemListUseCase: GetItemListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<CarItemState>(
        CarItemInfo(CarItem(manufacturer = "", carModel = "", price = 0))
    )
    val state = _state.asStateFlow()

    private lateinit var carItem: CarItem

    val manufacturerList =
        getItemListUseCase.getItemList(ManufacturerItem::class) as LiveData<List<ManufacturerItem>>

    val carModelList =
        getItemListUseCase.getItemList(CarModelItem::class) as LiveData<List<CarModelItem>>

    fun addCarItem(inputManufacturer: String, inputCarModel: String, inputPrice: String) {
        val manufacturer = parseString(inputManufacturer)
        val carModel = parseString(inputCarModel)
        val price = parseCount(inputPrice)
        val fieldsValid = validateInput(manufacturer, carModel, price)
        if (fieldsValid) {
            viewModelScope.launch {
                val carItem =
                    CarItem(
                        manufacturer = manufacturer,
                        carModel = carModel,
                        price = price
                    )
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

    fun editCarItem(inputManufacturer: String, inputCarModel: String, inputPrice: String) {
        val manufacturer = parseString(inputManufacturer)
        val carModel = parseString(inputCarModel)
        val price = parseCount(inputPrice)
        val fieldsValid = validateInput(manufacturer, carModel, price)
        if (fieldsValid) {
            viewModelScope.launch {
                carItem =
                    carItem.copy(
                        manufacturer = manufacturer,
                        carModel = carModel,
                        price = price
                    )
                editItemUseCase.editItem(carItem)
            }
            finishWork()
        }
    }

    private fun parseString(inputString: String?): String {
        return inputString?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(
        inputManufacturer: String,
        inputCarModel: String,
        inputPrice: Int
    ): Boolean {
        var result = true
        if (inputManufacturer.isBlank()) {
            _state.value = ErrorInputManufacturer(true)
            result = false
        }
        if (inputCarModel.isBlank()) {
            _state.value = ErrorInputCarModel(true)
            result = false
        }
        if (inputPrice <= 0) {
            _state.value = ErrorInputPrice(true)
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

    fun resetErrorInputPrice() {
        _state.value = ErrorInputPrice(false)
    }

    private fun finishWork() {
        _state.value = CloseScreen
    }

}
package com.example.cars.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cars.domain.models.ManufacturerItem
import com.example.cars.domain.useCases.item.AddItemUseCase
import com.example.cars.domain.useCases.item.EditItemUseCase
import com.example.cars.domain.useCases.item.GetItemUseCase
import com.example.cars.state.CloseManufacturerItemScreen
import com.example.cars.state.ErrorInputManufacturerName
import com.example.cars.state.ManufacturerItemInfo
import com.example.cars.state.ManufacturerItemState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ManufacturerItemViewModel @Inject constructor(
    private val addItemUseCase: AddItemUseCase,
    private val getItemUseCase: GetItemUseCase,
    private val editItemUseCase: EditItemUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<ManufacturerItemState>(
        ManufacturerItemInfo(ManufacturerItem(manufacturerName = ""))
    )
    val state = _state.asStateFlow()

    private lateinit var manufacturerItem: ManufacturerItem

    fun addManufacturerItem(inputManufacturer: String) {
        val manufacturer = parseString(inputManufacturer)
        val fieldsValid = validateInput(manufacturer)
        if (fieldsValid) {
            viewModelScope.launch {
                val manufacturerItem = ManufacturerItem(manufacturerName = manufacturer)
                addItemUseCase.addItem(manufacturerItem)
            }
            finishWork()
        }
    }

    fun getManufacturerItem(manufacturerItemId: Int) {
        viewModelScope.launch {
            manufacturerItem = getItemUseCase.getItem(
                ManufacturerItem::class,
                manufacturerItemId
            ) as ManufacturerItem
            _state.value = ManufacturerItemInfo(manufacturerItem)
        }
    }

    fun editManufacturerItem(inputManufacturer: String) {
        val manufacturer = parseString(inputManufacturer)
        val fieldsValid = validateInput(manufacturer)
        if (fieldsValid) {
            viewModelScope.launch {
                manufacturerItem = manufacturerItem.copy(manufacturerName = manufacturer)
                editItemUseCase.editItem(manufacturerItem)
            }
            finishWork()
        }
    }

    private fun parseString(inputString: String?): String {
        return inputString?.trim() ?: ""
    }

    private fun validateInput(inputManufacturer: String): Boolean {
        var result = true
        if (inputManufacturer.isBlank()) {
            _state.value = ErrorInputManufacturerName(true)
            result = false
        }
        return result
    }

    fun resetErrorInputManufacturerName() {
        _state.value = ErrorInputManufacturerName(false)
    }

    private fun finishWork() {
        _state.value = CloseManufacturerItemScreen
    }

}
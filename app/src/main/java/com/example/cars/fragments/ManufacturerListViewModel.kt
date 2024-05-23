package com.example.cars.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cars.domain.models.ManufacturerItem
import com.example.cars.domain.useCases.item.DeleteItemUseCase
import com.example.cars.domain.useCases.item.GetItemListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ManufacturerListViewModel @Inject constructor(
    private val getItemListUseCase: GetItemListUseCase,
    private val deleteItemUseCase: DeleteItemUseCase
) : ViewModel() {
    val manufacturerList =
        getItemListUseCase.getItemList(ManufacturerItem::class) as LiveData<List<ManufacturerItem>>

    fun deleteManufacturerItem(manufacturerItem: ManufacturerItem) {
        viewModelScope.launch {
            deleteItemUseCase.deleteItem(manufacturerItem)
        }
    }
}
package com.example.cars.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cars.domain.models.CarModelItem
import com.example.cars.domain.useCases.item.DeleteItemUseCase
import com.example.cars.domain.useCases.item.GetItemListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CarModelListViewModel @Inject constructor(
    private val getItemListUseCase: GetItemListUseCase,
    private val deleteItemUseCase: DeleteItemUseCase
) : ViewModel() {
    val carModelList =
        getItemListUseCase.getItemList(CarModelItem::class) as LiveData<List<CarModelItem>>

    fun deleteCarModelItem(carModelItem: CarModelItem) {
        viewModelScope.launch {
            deleteItemUseCase.deleteItem(carModelItem)
        }
    }
}
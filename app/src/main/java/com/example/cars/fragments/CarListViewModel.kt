package com.example.cars.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cars.domain.models.CarItem
import com.example.cars.domain.useCases.item.DeleteItemUseCase
import com.example.cars.domain.useCases.item.GetItemListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CarListViewModel @Inject constructor(
    private val getItemListUseCase: GetItemListUseCase,
    private val deleteItemUseCase: DeleteItemUseCase
) : ViewModel() {
    val carList = getItemListUseCase.getItemList(CarItem::class) as LiveData<List<CarItem>>

    fun deleteCarItem(carItem: CarItem) {
        viewModelScope.launch {
            deleteItemUseCase.deleteItem(carItem)
        }
    }
}
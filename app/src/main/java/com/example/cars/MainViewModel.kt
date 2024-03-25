package com.example.cars

import androidx.lifecycle.ViewModel
import com.example.cars.domain.useCases.GetCarListUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCarListUseCase: GetCarListUseCase
): ViewModel() {
    val carList = getCarListUseCase.getCarList()
}
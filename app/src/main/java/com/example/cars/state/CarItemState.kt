package com.example.cars.state

import com.example.cars.domain.models.CarItem

sealed class CarItemState
class CarItemInfo(val value: CarItem) : CarItemState()
class ErrorInputManufacturer(val value: Boolean) : CarItemState()
class ErrorInputCarModel(val value: Boolean) : CarItemState()
data object CloseScreen : CarItemState()


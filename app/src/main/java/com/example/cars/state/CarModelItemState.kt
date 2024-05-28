package com.example.cars.state

import com.example.cars.domain.models.CarModelItem

sealed class CarModelItemState
class CarModelItemInfo(val value: CarModelItem) : CarModelItemState()
class ErrorInputManufacturerItem(val value: Boolean) : CarModelItemState()
class ErrorInputCarModelName(val value: Boolean) : CarModelItemState()
data object CloseCarModelItemScreen : CarModelItemState()


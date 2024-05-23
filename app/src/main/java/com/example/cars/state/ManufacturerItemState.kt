package com.example.cars.state

import com.example.cars.domain.models.ManufacturerItem

sealed class ManufacturerItemState
class ManufacturerItemInfo(val value: ManufacturerItem) : ManufacturerItemState()
class ErrorInputManufacturerName(val value: Boolean) : ManufacturerItemState()
data object CloseManufacturerItemScreen : ManufacturerItemState()


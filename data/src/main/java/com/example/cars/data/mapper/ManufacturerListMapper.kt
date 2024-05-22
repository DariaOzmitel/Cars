package com.example.cars.data.mapper

import com.example.cars.data.database.manufacturers.ManufacturerInfoDbModel
import com.example.cars.domain.models.ManufacturerItem
import javax.inject.Inject

class ManufacturerListMapper @Inject constructor() {
    fun mapEntityToDbModel(manufacturerItem: ManufacturerItem) = ManufacturerInfoDbModel(
        id = manufacturerItem.id,
        manufacturer = manufacturerItem.manufacturerName
    )

    fun mapDbModelToEntity(manufacturerInfoDbModel: ManufacturerInfoDbModel) = ManufacturerItem(
        id = manufacturerInfoDbModel.id,
        manufacturerName = manufacturerInfoDbModel.manufacturer
    )

    fun mapListDbToListEntity(list: List<ManufacturerInfoDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}
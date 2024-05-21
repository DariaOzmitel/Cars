package com.example.cars.data.mapper

import com.example.cars.data.database.manufacturers.ManufacturerInfoDbModel
import com.example.cars.domain.models.ManufacturerItem2
import javax.inject.Inject

class ManufacturerListMapper @Inject constructor() {
    fun mapEntityToDbModel(manufacturerItem2: ManufacturerItem2) = ManufacturerInfoDbModel(
        id = manufacturerItem2.id,
        manufacturer = manufacturerItem2.manufacturerName
    )

    fun mapDbModelToEntity(manufacturerInfoDbModel: ManufacturerInfoDbModel) = ManufacturerItem2(
        id = manufacturerInfoDbModel.id,
        manufacturerName = manufacturerInfoDbModel.manufacturer
    )

    fun mapListDbToListEntity(list: List<ManufacturerInfoDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}
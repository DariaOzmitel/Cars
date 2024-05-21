package com.example.cars.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.cars.data.database.cars.CarInfoDao
import com.example.cars.data.database.manufacturers.ManufacturerInfoDao
import com.example.cars.data.mapper.CarListMapper
import com.example.cars.data.mapper.ManufacturerListMapper
import com.example.cars.domain.models.CarItem
import com.example.cars.domain.models.CarModelItem
import com.example.cars.domain.models.ManufacturerItem
import com.example.cars.domain.repository.CarRepository
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(
    private val carInfoDao: CarInfoDao,
    private val manufacturerInfoDao: ManufacturerInfoDao,
    private val carListMapper: CarListMapper,
    private val manufacturerListMapper: ManufacturerListMapper
) : CarRepository {
    override fun getCarList(): LiveData<List<CarItem>> = carInfoDao.getCarList().map {
        carListMapper.mapListDbToListEntity(it)
    }

    override suspend fun <T> addCar(itemClass: Class<T>, item: T) {
        if (itemClass.simpleName == "CarItem")
        carInfoDao.addCarItem(carListMapper.mapEntityToDbModel(item as CarItem))
    }

    override suspend fun addManufacturer(manufacturerItem: ManufacturerItem) {
        manufacturerInfoDao.addManufacturerItem(
            manufacturerListMapper.mapEntityToDbModel(
                manufacturerItem
            )
        )
    }

    override suspend fun addCarModel(carModelItem: CarModelItem) {
        TODO("Not yet implemented")
    }

    override suspend fun editCar(carItem: CarItem) {
        carInfoDao.addCarItem(carListMapper.mapEntityToDbModel(carItem))
    }

    override suspend fun deleteCar(carItem: CarItem) {
        carInfoDao.deleteCarItem(carItem.id)
    }

    override suspend fun getCarItem(carItemId: Int): CarItem {
        return carListMapper.mapDbModelToEntity(carInfoDao.getCarItem(carItemId))
    }
}
package com.example.cars.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.cars.data.database.cars.CarInfoDao
import com.example.cars.data.database.manufacturers.ManufacturerInfoDao
import com.example.cars.data.mapper.CarListMapper
import com.example.cars.data.mapper.ManufacturerListMapper
import com.example.cars.domain.models.CarItem
import com.example.cars.domain.models.CarModelItem2
import com.example.cars.domain.models.Item
import com.example.cars.domain.models.ManufacturerItem2
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

    override suspend fun addCar(item: Item) {
        when (item) {
            is CarItem -> {
                carInfoDao.addCarItem(carListMapper.mapCarEntityToDbModel(item))
            }

            else -> {}
        }
    }

    override suspend fun addManufacturer(manufacturerItem2: ManufacturerItem2) {
        manufacturerInfoDao.addManufacturerItem(
            manufacturerListMapper.mapEntityToDbModel(
                manufacturerItem2
            )
        )
    }

    override suspend fun addCarModel(carModelItem2: CarModelItem2) {
        TODO("Not yet implemented")
    }

    override suspend fun editCar(item: Item) {
        when (item) {
            is CarItem -> {
                carInfoDao.addCarItem(carListMapper.mapCarEntityToDbModel(item))
            }

            else -> {}
        }
    }

    override suspend fun deleteCar(item: Item) {
        when (item) {
            is CarItem -> {
                carInfoDao.deleteCarItem(item.id)
            }

            else -> {}
        }
    }

    override suspend fun getCarItem(itemId: Int): CarItem {
        return carListMapper.mapCarDbModelToEntity(carInfoDao.getCarItem(itemId))
    }
}
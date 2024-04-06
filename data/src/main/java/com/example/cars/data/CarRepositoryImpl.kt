package com.example.cars.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.cars.data.database.CarInfoDao
import com.example.cars.domain.models.CarItem
import com.example.cars.domain.repository.CarRepository
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(
    private val carInfoDao: CarInfoDao,
    private val mapper: CarListMapper
) : CarRepository {
    override fun getCarList(): LiveData<List<CarItem>> = carInfoDao.getCarList().map {
        mapper.mapListDbToListEntity(it)
    }

    override suspend fun addCar(carItem: CarItem) {
        carInfoDao.addCarItem(mapper.mapEntityToDbModel(carItem))
    }

    override suspend fun editCar(carItem: CarItem) {
        carInfoDao.addCarItem(mapper.mapEntityToDbModel(carItem))
    }

    override suspend fun deleteCar(carItem: CarItem) {
        carInfoDao.deleteCarItem(carItem.id)
    }

    override suspend fun getCarItem(carItemId: Int): CarItem {
        return mapper.mapDbModelToEntity(carInfoDao.getCarItem(carItemId))
    }
}
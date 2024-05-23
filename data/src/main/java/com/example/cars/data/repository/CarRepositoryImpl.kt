package com.example.cars.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.cars.data.database.cars.CarInfoDao
import com.example.cars.data.database.manufacturers.ManufacturerInfoDao
import com.example.cars.data.mapper.CarListMapper
import com.example.cars.data.mapper.ManufacturerListMapper
import com.example.cars.domain.models.CarItem
import com.example.cars.domain.models.Item
import com.example.cars.domain.models.ManufacturerItem
import com.example.cars.domain.repository.CarRepository
import javax.inject.Inject
import kotlin.reflect.KClass

class CarRepositoryImpl @Inject constructor(
    private val carInfoDao: CarInfoDao,
    private val manufacturerInfoDao: ManufacturerInfoDao,
    private val carListMapper: CarListMapper,
    private val manufacturerListMapper: ManufacturerListMapper
) : CarRepository {
    override fun <T : Item> getItemList(itemClass: KClass<T>): LiveData<List<Item>> {
        return when (itemClass) {
            CarItem::class -> {
                carInfoDao.getCarList().map {
                    carListMapper.mapListDbToListEntity(it)
                }
            }

            ManufacturerItem::class -> {
                manufacturerInfoDao.getManufacturerList().map {
                    manufacturerListMapper.mapListDbToListEntity(it)
                }
            }

            else -> {
                carInfoDao.getCarList().map {
                    carListMapper.mapTestListDbToListEntity(it)
                }
            }
        }

    }


    override suspend fun addItem(item: Item) {
        when (item) {
            is CarItem -> {
                carInfoDao.addCarItem(carListMapper.mapCarEntityToDbModel(item))
            }

            is ManufacturerItem -> {
                manufacturerInfoDao.addManufacturerItem(
                    manufacturerListMapper.mapEntityToDbModel(
                        item
                    )
                )
            }

            else -> {}
        }
    }

    override suspend fun editItem(item: Item) {
        when (item) {
            is CarItem -> {
                carInfoDao.addCarItem(carListMapper.mapCarEntityToDbModel(item))
            }

            is ManufacturerItem -> {
                manufacturerInfoDao.addManufacturerItem(
                    manufacturerListMapper.mapEntityToDbModel(
                        item
                    )
                )
            }

            else -> {}
        }
    }

    override suspend fun deleteItem(item: Item) {
        when (item) {
            is CarItem -> {
                carInfoDao.deleteCarItem(item.id)
            }

            is ManufacturerItem -> {
                manufacturerInfoDao.deleteManufacturerItem(item.id)
            }

            else -> {}
        }
    }

    override suspend fun <T : Item> getItem(itemClass: KClass<T>, itemId: Int): Item {
        return when (itemClass) {
            CarItem::class -> {
                carListMapper.mapCarDbModelToEntity(carInfoDao.getCarItem(itemId))
            }

            ManufacturerItem::class -> {
                manufacturerListMapper.mapDbModelToEntity(
                    manufacturerInfoDao.getManufacturerItem(
                        itemId
                    )
                )
            }

            else -> {
                carListMapper.mapTestDbModelToEntity(carInfoDao.getCarItem(itemId))
            }
        }
    }
}
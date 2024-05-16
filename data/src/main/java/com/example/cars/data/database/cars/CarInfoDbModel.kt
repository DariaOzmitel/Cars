package com.example.cars.data.database.cars

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "carInfo")
data class CarInfoDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "manufacturer")
    val manufacturer: String,
    @ColumnInfo(name = "carModel")
    val carModel: String
)
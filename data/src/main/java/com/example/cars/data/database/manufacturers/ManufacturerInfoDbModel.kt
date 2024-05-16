package com.example.cars.data.database.manufacturers

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "manufacturerInfo")
data class ManufacturerInfoDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "manufacturer")
    val manufacturer: String
)
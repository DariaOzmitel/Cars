package com.example.cars.data.database.manufacturers

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "manufacturerInfo", indices = [Index(value = ["manufacturer"], unique = true)])
data class ManufacturerInfoDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "manufacturer")
    val manufacturer: String
)
package com.example.cars.data.database.carModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.cars.data.database.manufacturers.ManufacturerInfoDbModel

@Entity(
    tableName = "carModelInfo",
    indices = [Index(value = ["manufacturerName", "carModel"], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = ManufacturerInfoDbModel::class,
            parentColumns = ["manufacturer"],
            childColumns = ["manufacturerName"]
        )
    ]
)
data class CarModelInfoDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "manufacturerName")
    val manufacturerName: String,
    @ColumnInfo(name = "carModel")
    val carModel: String
)
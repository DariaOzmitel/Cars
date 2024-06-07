package com.example.cars.data.database.cars

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.cars.data.database.carModel.CarModelInfoDbModel

@Entity(
    tableName = "carInfo",
    indices = [Index("id")],
    foreignKeys = [
        ForeignKey(
            entity = CarModelInfoDbModel::class,
            parentColumns = ["manufacturerName", "carModel"],
            childColumns = ["manufacturer", "carModel"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CarInfoDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "manufacturer")
    val manufacturer: String,
    @ColumnInfo(name = "carModel")
    val carModel: String,
    @ColumnInfo(name = "price")
    val price: Int
)
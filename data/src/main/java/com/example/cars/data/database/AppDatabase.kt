package com.example.cars.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cars.data.database.cars.CarInfoDao
import com.example.cars.data.database.cars.CarInfoDbModel
import com.example.cars.data.database.manufacturers.ManufacturerInfoDao
import com.example.cars.data.database.manufacturers.ManufacturerInfoDbModel

@Database(
    entities = [CarInfoDbModel::class, ManufacturerInfoDbModel::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    companion object {

        private var db: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun carInfoDao(): CarInfoDao
    abstract fun manufacturerInfoDao(): ManufacturerInfoDao
}
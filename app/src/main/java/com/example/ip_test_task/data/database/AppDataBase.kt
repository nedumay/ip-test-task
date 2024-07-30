package com.example.ip_test_task.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import javax.inject.Inject

/**
 * @author Samim
 * Абстрактный класс для создания базы данных
 */
@Database(entities = [ProductInfoDbModel::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    @Inject
    internal lateinit var appDataBase: AppDatabase

    abstract fun productInfoDao(): ProductInfoDao
}
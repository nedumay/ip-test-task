package com.example.ip_test_task.data.database

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

/**
 * @author Samim
 * Интерфейс Dao для работы с базой данных
 */

@Dao
interface ProductInfoDao {

    // Функция получения всех записей
    @Query("SELECT * FROM item")
    suspend fun getAll(): List<ProductInfoDbModel>

    // Функция получения определенной записи
    @Query("SELECT * FROM item WHERE id = :id")
    suspend fun getItem(id: Int): ProductInfoDbModel

    //Функция удаления определенной записи
    @Query("DELETE FROM item WHERE id = :id")
    suspend fun deleteItem(id: Int)

    // Функция обновления определенной записи
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateItem(item: ProductInfoDbModel)
}
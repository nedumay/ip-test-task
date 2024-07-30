package com.example.ip_test_task.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

/**
 * @author Samim
 * Дата класс для хранения информации о продукте
 */
@Entity(tableName = "item")
data class ProductInfoDbModel(
    @PrimaryKey
    @NotNull
    val id: Int,
    @NotNull
    val name: String,
    @NotNull
    val time: Int,
    @NotNull
    val tags: String,
    @NotNull
    val amount: Int
)
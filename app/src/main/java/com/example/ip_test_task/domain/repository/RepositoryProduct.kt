package com.example.ip_test_task.domain.repository

import com.example.ip_test_task.domain.model.ProductInfo

/**
 * @author Samim
 * Интерфейс репозитория для работы со списком товаров
 */
interface RepositoryProduct {

    // Удаление item из базы данных
    suspend fun deleteProduct(productInfo: ProductInfo)

    // Обновление item в базе данных
    suspend fun updateProduct(productInfo: ProductInfo)

    // Получение списка товаров
    suspend fun getProductList(): List<ProductInfo>


}
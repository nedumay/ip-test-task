package com.example.ip_test_task.data.repository

import android.util.Log
import com.example.ip_test_task.common.Resource
import com.example.ip_test_task.data.database.ProductInfoDao
import com.example.ip_test_task.data.mapper.MapperProduct
import com.example.ip_test_task.domain.model.ProductInfo
import com.example.ip_test_task.domain.repository.RepositoryProduct
import javax.inject.Inject

/**
 * @author Samim
 * Реализация интерфейса репозитория
 */
class RepositoryProductImpl @Inject constructor(
    private val mapper: MapperProduct,
    private val productInfoDao: ProductInfoDao
) : RepositoryProduct {

    override suspend fun deleteProduct(productInfo: ProductInfo) {
        productInfoDao.deleteItem(productInfo.id)
    }

    override suspend fun updateProduct(productInfo: ProductInfo) {
        productInfoDao.updateItem(mapper.mapEntityToDbModel(productInfo))
    }

    override suspend fun getProductList(): List<ProductInfo> {
        val dbModel = productInfoDao.getAll()
        return dbModel.map { mapper.mapDbModelToEntity(it) }
    }
}